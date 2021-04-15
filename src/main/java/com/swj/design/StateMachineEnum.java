package com.swj.design;

public enum StateMachineEnum {

    Made {
        @Override
        public StateMachineEnum agree() {
            return Verified;
        }

        @Override
        public StateMachineEnum refuse() {
            return this;
        }

        @Override
        public String state() {
            return "Made";
        }
    },
    Verified {
        @Override
        public StateMachineEnum agree() {
            return Signed;
        }

        @Override
        public StateMachineEnum refuse() {
            return Made;
        }

        @Override
        public String state() {
            return "Verified";
        }
    },
    Signed {
        @Override
        public StateMachineEnum agree() {
            return Exported;
        }

        @Override
        public StateMachineEnum refuse() {
            return Verified;
        }

        @Override
        public String state() {
            return "Signed";
        }
    },
    Exported {
        @Override
        public StateMachineEnum agree() {
            return Done;
        }

        @Override
        public StateMachineEnum refuse() {
            return Signed;
        }

        @Override
        public String state() {
            return "Exported";
        }
    },
    Done {
        @Override
        public StateMachineEnum agree() {
            return this;
        }

        @Override
        public StateMachineEnum refuse() {
            return Exported;
        }

        @Override
        public String state() {
            return "Done";
        }
    }
    ;

    public abstract StateMachineEnum agree();
    public abstract StateMachineEnum refuse();
    public abstract String state();
}