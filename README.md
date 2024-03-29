# Ecommerce Challenge - Spring
## Descripción
La aplicación es un sitio básico de ecommerce diseñado para el cliente, un asesor financiero que busca modernizarse. Permite el registro de usuarios, ofrece dos tipos de suscripciones (mensual y anual), implementa Stripe como gateway de pago, y proporciona una vista para poder registrarse, iniciar sesión, ver perfil y suscribirse.

## Instalación
### Frontend (Vue.js)
* Ejecutar npm install para instalar las dependencias.
* Ejecutar npm run dev para iniciar la aplicación Vue.js.
### Backend (Spring con Java y Maven)
* Asegurarse de tener Java y Maven instalados.
* Configurar la base de datos PostgreSQL y actualizar la configuración en el archivo application.properties.
* Ejecutar la aplicación Spring.
### Configuración de Escucha de Eventos Stripe
1. Ingresar a la cuenta de Stripe en [stripe.com](https://stripe.com).
2. Abrir la CLI de Stripe.
3. Iniciar sesión con la cuenta de Stripe ejecutando el comando: 
   ```bash
   stripe login
4. Ejecutar el siguiente comando
   ```bash
   stripe listen --forward-to localhost:8080/order/webhook

## Funcionamiento Básico
### Registro de Usuarios (/auth/register - POST)
* Endpoint para registrar nuevos usuarios.
* Parámetros de Entrada: firstName, lastName, email, password, receiveNewsletters.
* Devuelve: Token de autenticación.
* Requiere autenticación: NO

### Inicio de Sesión (/auth/login - POST)
* Endpoint para iniciar sesión de usuarios existentes.
* Parámetros de Entrada: email, password.
* Devuelve: Token de autenticación.
* Requiere autenticación: NO

### Compra de Suscripción Mensual (/order/charge - POST)
* Realiza el pago y devuelve una PaymentResponse.
* Parámetros de Entrada: amount, currency, description, token.
* Devuelve: id, amount, currency, status.
* Requiere autenticación: SI

### Checkout para Suscripción Anual (/order/checkout - POST)
* Inicia el proceso de compra para una suscripción anual.
* Parámetros de Entrada: subscriptionType, userId.
* Devuelve: URL de la página de compra de suscripción.
* Requiere autenticación: SI

### Webhook de Pago (/order/webhook - POST)
* Endpoint para manejar eventos de pago de Stripe.
* Devuelve: Respuesta 200 en caso de éxito.
* Requiere autenticación: NO

### Registro de Suscripción (/subscription/ - POST)
* Registra una nueva suscripción.
* Parámetros de Entrada: userId, subscriptionType
* Devuelve: Mensaje de operación exitosa o fallida.
* Requiere autenticación: SI

### Consulta de Suscripción por Usuario (/subscription/$userId - GET)
* Obtiene detalles de la suscripción de un usuario específico.
* Parámetros de Entrada: userId
* Devuelve: id, type, price, startDate, expirationDate.
* Requiere autenticación: SI

### Consulta de Tipos de Suscripción (/subscription/types - GET)
* Obtiene una lista de tipos de suscripción disponibles.
* Devuelve: Lista de suscripciones con name, price, description.
* Requiere autenticación: NO

### Perfil de Usuario (/user/$id - GET)
* Obtiene detalles de un usuario específico.
* Párametros de Entrada: id
* Devuelve: id, firstName, lastName, email, receiveNewsletter
* Requiere autenticación: SI

### Perfil de Usuario (/user/me - GET)
* Obtiene detalles de un usuario específico.
* Párametros de Entrada: token
* Devuelve: id, firstName, lastName, email, receiveNewsletter
* Requiere autenticación: SI

## Tecnologías Utilizadas
* Frontend: Vue.js
* Backend: Spring con Java y Maven
* Base de Datos: PostgreSQL
## Consideraciones de Seguridad
* Contraseñas se almacenan de manera segura.
* Integración de Stripe
* Utiliza Stripe como gateway de pago para manejar suscripciones de manera segura.
* Métodos cuentan con seguridad para acceder solamente si están autenticados.
## Análisis y Futuras Mejoras
* Se registran pagos exitosos en la base de datos para futuros análisis y campañas de marketing.
