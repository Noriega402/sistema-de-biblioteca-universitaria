
import { login, register } from "./auth.js";

// LOGIN
const loginForm = document.querySelector('[data-auth="login-form"]');
if (loginForm) {
  const emailEl = loginForm.querySelector('[data-auth="email"]');
  const passEl  = loginForm.querySelector('[data-auth="password"]');
  const errorEl = loginForm.querySelector('[data-auth="error"]');

  loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    if (errorEl) errorEl.textContent = "";
    try {
      await login(emailEl.value.trim(), passEl.value);
      const target = loginForm.getAttribute("data-redirect") || "./dashboard.html";
      window.location.href = target;
    } catch (err) {
      if (errorEl) errorEl.textContent = err.message || "Error al iniciar sesión";
      else alert(err.message || "Error al iniciar sesión");
    }
  });
}

// REGISTER
const regForm = document.querySelector('[data-auth="register-form"]');
if (regForm) {
  const nombreEl    = regForm.querySelector('[data-auth="nombre"]');
  const apellidosEl = regForm.querySelector('[data-auth="apellidos"]');
  const emailEl     = regForm.querySelector('[data-auth="email"]');
  const passEl      = regForm.querySelector('[data-auth="password"]');
  const errorEl     = regForm.querySelector('[data-auth="error"]');

  regForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    if (errorEl) errorEl.textContent = "";
    try {
      await register({
        nombre: nombreEl?.value?.trim(),
        apellidos: apellidosEl?.value?.trim(),
        email: emailEl?.value?.trim(),
        password: passEl?.value,
      });
      const target = regForm.getAttribute("data-redirect") || "./index.html";
      window.location.href = target;
    } catch (err) {
      if (errorEl) errorEl.textContent = err.message || "No se pudo registrar";
      else alert(err.message || "No se pudo registrar");
    }
  });
}
