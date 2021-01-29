import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/data/";

class UserService {
  getHome() {
    return axios.get(API_URL + "home");
  }

  getUserprofile(uid) {
    return axios.get(API_URL + uid, { headers: authHeader() });
  }

  getProfile() {
    return axios.get(API_URL + "profile", { headers: authHeader() });
  }

  getUsersList() {
    return axios.get(API_URL + "users", { headers: authHeader() });
  }

  getAdminPage() {
    return axios.get(API_URL + "admin", { headers: authHeader() });
  }
}

export default new UserService();
