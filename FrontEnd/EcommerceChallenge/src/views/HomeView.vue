<script>
import axios from 'axios';

export default {
  setup() {}
};
</script>

<template>
  <div class="d-flex flex-column gradient-custom text-white min-vh-100">
    <div class="container py-5">
      <h1 class="display-4" id="ecommerce-challenge-spring">Ecommerce Challenge - Spring</h1>
      <h2 class="mt-4" id="descripci-n">Descripción</h2>
      <p class="lead">
        La aplicación es un sitio básico de ecommerce diseñado para el cliente, un asesor financiero
        que busca modernizarse. Permite el registro de usuarios, ofrece dos tipos de suscripciones
        (mensual y anual), implementa Stripe como gateway de pago, y proporciona una vista para
        poder registrarse, iniciar sesión, ver perfil y suscribirse.
      </p>
      <h2 id="instalaci-n">Instalación</h2>
      <h3 id="frontend-vue-js-">Frontend (Vue.js)</h3>
      <ul>
        <li>Ejecutar npm install para instalar las dependencias.</li>
        <li>
          Ejecutar npm run dev para iniciar la aplicación Vue.js.
          <h3 id="backend-spring-con-java-y-maven-">Backend (Spring con Java y Maven)</h3>
        </li>
        <li>Asegurarse de tener Java y Maven instalados.</li>
        <li>
          Configurar la base de datos PostgreSQL y actualizar la configuración en el archivo
          application.properties.
        </li>
        <li>
          Ejecutar la aplicación Spring.
          <h3 id="configuraci-n-de-escucha-de-eventos-stripe">
            Configuración de Escucha de Eventos Stripe
          </h3>
        </li>
        <li>Ingresar a la cuenta de Stripe en <a href="https://stripe.com">stripe.com</a>.</li>
        <li>Abrir la CLI de Stripe.</li>
        <li>Iniciar sesión con la cuenta de Stripe ejecutando el comando: stripe login</li>
        <li>
          Ejecutar el siguiente comando stripe listen --forward-to
          localhost:8080/order/webhook
        </li>
      </ul>
      <h2 id="funcionamiento-b-sico">Funcionamiento Básico</h2>
      <h3 id="registro-de-usuarios-auth-register-post-">
        Registro de Usuarios (/auth/register - POST)
      </h3>
      <ul>
        <li>Endpoint para registrar nuevos usuarios.</li>
        <li>Parámetros de Entrada: firstName, lastName, email, password, receiveNewsletters.</li>
        <li>Devuelve: Token de autenticación.</li>
        <li>Requiere autenticación: NO</li>
      </ul>
      <h3 id="inicio-de-sesi-n-auth-login-post-">Inicio de Sesión (/auth/login - POST)</h3>
      <ul>
        <li>Endpoint para iniciar sesión de usuarios existentes.</li>
        <li>Parámetros de Entrada: email, password.</li>
        <li>Devuelve: Token de autenticación.</li>
        <li>Requiere autenticación: NO</li>
      </ul>
      <h3 id="compra-de-suscripci-n-mensual-order-charge-post-">
        Compra de Suscripción Mensual (/order/charge - POST)
      </h3>
      <ul>
        <li>Realiza el pago y devuelve una PaymentResponse.</li>
        <li>Parámetros de Entrada: amount, currency, description, token.</li>
        <li>Devuelve: id, amount, currency, status.</li>
        <li>Requiere autenticación: SI</li>
      </ul>
      <h3 id="checkout-para-suscripci-n-anual-order-checkout-post-">
        Checkout para Suscripción Anual (/order/checkout - POST)
      </h3>
      <ul>
        <li>Inicia el proceso de compra para una suscripción anual.</li>
        <li>Parámetros de Entrada: subscriptionType, userId.</li>
        <li>Devuelve: URL de la página de compra de suscripción.</li>
        <li>Requiere autenticación: SI</li>
      </ul>
      <h3 id="webhook-de-pago-order-webhook-post-">Webhook de Pago (/order/webhook - POST)</h3>
      <ul>
        <li>Endpoint para manejar eventos de pago de Stripe.</li>
        <li>Devuelve: Respuesta 200 en caso de éxito.</li>
        <li>Requiere autenticación: NO</li>
      </ul>
      <h3 id="registro-de-suscripci-n-subscription-post-">
        Registro de Suscripción (/subscription/ - POST)
      </h3>
      <ul>
        <li>Registra una nueva suscripción.</li>
        <li>Parámetros de Entrada: userId, subscriptionType</li>
        <li>Devuelve: Mensaje de operación exitosa o fallida.</li>
        <li>Requiere autenticación: SI</li>
      </ul>
      <h3 id="consulta-de-suscripci-n-por-usuario-subscription-userid-get-">
        Consulta de Suscripción por Usuario (/subscription/$userId - GET)
      </h3>
      <ul>
        <li>Obtiene detalles de la suscripción de un usuario específico.</li>
        <li>Parámetros de Entrada: userId</li>
        <li>Devuelve: id, type, price, startDate, expirationDate.</li>
        <li>Requiere autenticación: SI</li>
      </ul>
      <h3 id="consulta-de-tipos-de-suscripci-n-subscription-types-get-">
        Consulta de Tipos de Suscripción (/subscription/types - GET)
      </h3>
      <ul>
        <li>Obtiene una lista de tipos de suscripción disponibles.</li>
        <li>Devuelve: Lista de suscripciones con name, price, description.</li>
        <li>Requiere autenticación: NO</li>
      </ul>
      <h3 id="perfil-de-usuario-user-id-get-">Perfil de Usuario (/user/$id - GET)</h3>
      <ul>
        <li>Obtiene detalles de un usuario específico.</li>
        <li>Párametros de Entrada: id</li>
        <li>Devuelve: id, firstName, lastName, email, receiveNewsletter</li>
        <li>Requiere autenticación: SI</li>
      </ul>
      <h3 id="perfil-de-usuario-user-me-get-">Perfil de Usuario (/user/me - GET)</h3>
      <ul>
        <li>Obtiene detalles de un usuario específico.</li>
        <li>Párametros de Entrada: token</li>
        <li>Devuelve: id, firstName, lastName, email, receiveNewsletter</li>
        <li>Requiere autenticación: SI</li>
      </ul>
      <h2 id="tecnolog-as-utilizadas">Tecnologías Utilizadas</h2>
      <ul>
        <li>Frontend: Vue.js</li>
        <li>Backend: Spring con Java y Maven</li>
        <li>
          Base de Datos: PostgreSQL
          <h2 id="consideraciones-de-seguridad">Consideraciones de Seguridad</h2>
        </li>
        <li>Contraseñas se almacenan de manera segura.</li>
        <li>Integración de Stripe</li>
        <li>Utiliza Stripe como gateway de pago para manejar suscripciones de manera segura.</li>
        <li>
          Métodos cuentan con seguridad para acceder solamente si están autenticados.
          <h2 id="an-lisis-y-futuras-mejoras">Análisis y Futuras Mejoras</h2>
        </li>
        <li>
          Se registran pagos exitosos en la base de datos para futuros análisis y campañas de
          marketing.
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.gradient-custom {
  background: #6a11cb;
  background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
  background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
  color: white;
  padding: 20px;
  border-radius: 10px;
}
</style>
