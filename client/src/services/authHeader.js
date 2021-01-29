export default function authHeader() {
  let user = JSON.parse(localStorage.getItem("user"));

  return user && user.accessToken
    ? { Authorization: "Bearer " + user.accessToken }
    : {};
}
