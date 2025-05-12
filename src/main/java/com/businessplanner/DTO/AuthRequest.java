package com.businessplanner.DTO;

public class AuthRequest { 

        private String login;
        private String password;
        private String email;
    
        public AuthRequest(String login, String password, String email) {
            this.login = login;
            this.password = password;
            this.email = email;

        }
    
        public AuthRequest() {
        }
    
        public String getLogin() {
            return login;
        }

        public String getEmail() {
                return email;
            }
    
        public void setLogin(String login) {
            this.login = login;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
}
