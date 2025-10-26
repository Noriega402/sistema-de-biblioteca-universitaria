
import { API_BASE, USE_HTTPONLY_COOKIE } from "./config.js";

async function request(path, { method="GET", body, auth=false } = {}) {
  const headers = { "Content-Type": "application/json" };
  const opts = { method, headers };

  if (USE_HTTPONLY_COOKIE) {
    // No se usa en este preset, pero lo dejamos por compatibilidad.
    opts.credentials = "include";
  } else if (auth) {
    const token = sessionStorage.getItem("accessToken");
    if (token) headers.Authorization = `Bearer ${token}`;
  }

  if (body) opts.body = JSON.stringify(body);

  const res = await fetch(`${API_BASE}${path}`, opts);
  let data = {};
  try { data = await res.json(); } catch (_) {}

  if (!res.ok) {
    const msg = data.message || data.error || `HTTP ${res.status}`;
    throw new Error(msg);
  }
  return data;
}

export const api = {
  post: (path, body, auth=false) => request(path, { method: "POST", body, auth }),
  get:  (path, auth=false)       => request(path, { method: "GET",  auth }),
};
