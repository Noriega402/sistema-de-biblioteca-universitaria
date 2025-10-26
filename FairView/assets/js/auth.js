
import { api } from "./api.js";
import { USE_HTTPONLY_COOKIE } from "./config.js";

export async function login(email, password) {
  const r = await api.post("/auth/login", { email, password }, false);
  // Esperamos { accessToken, user }
  if (r?.accessToken) sessionStorage.setItem("accessToken", r.accessToken);
  if (r?.user) sessionStorage.setItem("user", JSON.stringify(r.user));
  return r;
}

export async function register({ nombre, apellidos, email, password }) {
  // Si tu backend hace autologin al registrar y devuelve tokens, también se guardarán.
  const r = await api.post("/auth/register", { nombre, apellidos, email, password }, false);
  if (r?.accessToken) sessionStorage.setItem("accessToken", r.accessToken);
  if (r?.user) sessionStorage.setItem("user", JSON.stringify(r.user));
  return r;
}

export function logout() {
  sessionStorage.removeItem("accessToken");
  sessionStorage.removeItem("user");
}
