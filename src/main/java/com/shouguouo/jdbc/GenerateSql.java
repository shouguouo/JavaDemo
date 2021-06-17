package com.shouguouo.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shouguouo~
 * @date 2020/3/3 - 22:41
 */
public class GenerateSql {

    public static final String INSERT_PATTERN                       =   "insert into %s (%s)\nvalues (%s);\n\n";
    public static final String SELECT_TSYS_MENU_PATTERN             =   "select * from tsys_menu t where t.menu_code = '%s'";
    public static final String SELECT_TSYS_TRANS_PATTERN            =   "select * from tsys_trans t where t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s')";
    public static final String SELECT_TSYS_SUBTRANS_PATTERN         =   "select * from tsys_subtrans t where t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s')";
    public static final String SELECT_TSYS_ROLE_RIGHT_PATTERN       =   "select * from tsys_role_right t where t.role_code='admin' and t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s')";
    public static final String SELECT_TSYS_USER_RIGHT_PATTERN       =   "select * from tsys_user_right t where t.user_id='admin' and t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s')";
    public static final String DELETE_TSYS_TRANS_PATTERN            =   "delete from tsys_trans t where t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s');\n";
    public static final String DELETE_TSYS_SUBTRANS_PATTERN         =   "delete from tsys_subtrans t where t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s');\n";
    public static final String DELETE_TSYS_ROLE_RIGHT_PATTERN       =   "delete from tsys_role_right t where t.role_code='admin' and t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s');\n";
    public static final String DELETE_TSYS_USER_RIGHT_PATTERN       =   "delete from tsys_user_right t where t.user_id='admin' and t.trans_code in (select t.trans_code from tsys_menu t where t.menu_code = '%s');\n";
    public static final String DELETE_TSYS_MENU_PATTERN             =   "delete from tsys_menu t where t.menu_code = '%s';\n\n";


    public static final HashMap<String, String> COLUMNS = new HashMap<>();
    public static final HashMap<String, List<String>> COLUMNS_LIST = new HashMap<>();
    public static final HashMap<String, String> SELECT_PATTERN_MAP = new HashMap<String, String>() {{
        put("tsys_menu", SELECT_TSYS_MENU_PATTERN);
        put("tsys_trans", SELECT_TSYS_TRANS_PATTERN);
        put("tsys_subtrans", SELECT_TSYS_SUBTRANS_PATTERN);
        put("tsys_role_right", SELECT_TSYS_ROLE_RIGHT_PATTERN);
        put("tsys_user_right", SELECT_TSYS_USER_RIGHT_PATTERN);
    }};


    private final Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public GenerateSql(Connection conn) throws SQLException {
        if (conn == null) {
            throw new RuntimeException("connection can't be null");
        }
        this.conn = conn;
        init();
    }

    private void init() throws SQLException {
        StringBuilder sb = new StringBuilder();
        for (String table : SELECT_PATTERN_MAP.keySet()) {
            sb.delete(0, sb.length());
            resultSet = conn.getMetaData().getColumns("", "%", table.toUpperCase(), "%");
            List<String> columnsList = new ArrayList<>();
            while (resultSet.next()) {
                sb.append(resultSet.getString("column_name")).append(", ");
                columnsList.add(resultSet.getString("column_name"));
            }
            sb.delete(sb.length() - 2, sb.length());
            COLUMNS.put(table, sb.toString());
            COLUMNS_LIST.put(table, columnsList);
        }
    }
    public void build() throws SQLException, IOException {
        try{
            if (this.conn == null) {
                throw new RuntimeException("connection can't be null");
            }
            write(generateSql(buildTreeRec("BIZFRAME", getMenuCode(null))));
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void build(String menuCode) throws SQLException, IOException {
        try{
            write(generateSql(getMenuCode(menuCode)));
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    private void write(String sb) throws IOException {
        try (OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + File.separator + "xbrl_menu.sql"), "GBK")) {
            sb += "commit;\n";
            outputStream.write(sb);
        }
    }

    private String generateSql(List<MenuDTO> menuDTOList) throws SQLException {
        StringBuilder sb = new StringBuilder();
        for (MenuDTO dto : menuDTOList) {
            // 删除语句
            sb.append("------------------------------------").append(dto.getMenuName()).append("------------------------------------\n");
            sb.append(String.format(DELETE_TSYS_TRANS_PATTERN, dto.getMenuCode()));
            sb.append(String.format(DELETE_TSYS_SUBTRANS_PATTERN, dto.getMenuCode()));
            sb.append(String.format(DELETE_TSYS_ROLE_RIGHT_PATTERN, dto.getMenuCode()));
            sb.append(String.format(DELETE_TSYS_USER_RIGHT_PATTERN, dto.getMenuCode()));
            sb.append(String.format(DELETE_TSYS_MENU_PATTERN, dto.getMenuCode()));

            for (Map.Entry<String, String> entry : SELECT_PATTERN_MAP.entrySet()) {
                statement = conn.prepareStatement(String.format(entry.getValue(), dto.getMenuCode()));
                resultSet = statement.executeQuery();
                StringBuilder values = new StringBuilder();
                while (resultSet.next()) {
                    values.delete(0, values.length());
                    for (String column : COLUMNS_LIST.get(entry.getKey())) {
                        values.append(transferColumnType(resultSet.getObject(column))).append(", ");
                    }
                    if (values.length() > 2) {
                        values.delete(values.length() - 2, values.length());
                    }
                    sb.append(String.format(INSERT_PATTERN, entry.getKey(), COLUMNS.get(entry.getKey()), values.toString()));
                }
            }

            if (dto.getChildren().size() > 0) {
                sb.append(generateSql(dto.getChildren()));
            }
        }
        return sb.toString();
    }


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");

    public String transferColumnType (Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Date) {
            return LocalDateTime.ofInstant(((java.sql.Date)obj).toInstant(), ZoneId.systemDefault()).format(DATE_TIME_FORMATTER);
        } else if (obj instanceof Number) {
            return obj.toString();
        } else {
            return "'" + obj + "'";
        }
    }

    private List<MenuDTO> buildTreeRec(String rootCode, List<MenuDTO> menuDTOList) {
        List<MenuDTO> children = menuDTOList.stream().filter(x -> x.getParentCode().equals(rootCode)).sorted(Comparator.comparingInt(MenuDTO::getOrderNo)).collect(Collectors.toList());
        if (children.size() > 0) {
            children.forEach(x -> x.getChildren().addAll(buildTreeRec(x.menuCode, menuDTOList)));
        }
        return children;
    }

    private List<MenuDTO> getMenuCode(String menuCode) throws SQLException {

        List<MenuDTO> list = new ArrayList<>();
        // 排除操作员中心本身的菜单
        String sql = "select menu_code, menu_name, parent_code, tree_idx, order_no from tsys_menu t where t.app_code <> 'BIZFRAME' and t.menu_code <> 'BIZFRAME' and t.is_hidden <> '1'"
            + (menuCode == null ? "" : (" and t.menu_code = '" + menuCode + "'"));
        statement = conn.prepareStatement(sql);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuCode(resultSet.getString("menu_code"));
            menuDTO.setParentCode(resultSet.getString("parent_code"));
            menuDTO.setTreeIdx(resultSet.getString("tree_idx"));
            menuDTO.setOrderNo(resultSet.getInt("order_no"));
            menuDTO.setMenuName(resultSet.getString("menu_name"));
            list.add(menuDTO);
        }
        return list;
    }

    private static class MenuDTO {
        private String menuCode;

        private String menuName;

        private String parentCode;

        private String treeIdx;

        private Integer orderNo;

        private List<MenuDTO> children = new ArrayList<>();

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public String getMenuCode() {
            return menuCode;
        }

        public void setMenuCode(String menuCode) {
            this.menuCode = menuCode;
        }

        public String getParentCode() {
            return parentCode;
        }

        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        public String getTreeIdx() {
            return treeIdx;
        }

        public void setTreeIdx(String treeIdx) {
            this.treeIdx = treeIdx;
        }

        public Integer getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Integer orderNo) {
            this.orderNo = orderNo;
        }

        public List<MenuDTO> getChildren() {
            return children;
        }

        public void setChildren(List<MenuDTO> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "MenuDTO{" +
                "menuCode='" + menuCode + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", treeIdx='" + treeIdx + '\'' +
                ", orderNo=" + orderNo +
                '}';
        }
    }
}
