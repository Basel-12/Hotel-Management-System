package org.example.entites;

public abstract class Person {
        private int id;
        private String fname;
        private String lname;
        private String ssn;
        private String phone;

        public Person() {
        }

        public Person(int id, String fname, String lname, String ssn, String phone) {
                this.id = id;
                this.fname = fname;
                this.lname = lname;
                this.ssn = ssn;
                this.phone = phone;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getFname() {
                return fname;
        }

        public void setFname(String fname) {
                this.fname = fname;
        }

        public String getLname() {
                return lname;
        }

        public void setLname(String lname) {
                this.lname = lname;
        }

        public String getSsn() {
                return ssn;
        }

        public void setSsn(String ssn) {
                this.ssn = ssn;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        @Override
        public String toString() {
                return  id + " " + fname + " " + lname + " " + ssn  + " " + phone + " ";

        }
}
