<template>
  <section id="Profile" class="vh-100 gradient-custom">
    <h1>Profile</h1>
    <div class="user-info">
      <p>Name: {{ user.name }}</p>
      <p>Email: {{ user.email }}</p>
      <p>Receive Newsletters: {{ user.receiveNewsletters ? 'Yes' : 'No' }}</p>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      user: {
        name: 'John Doe',
        email: 'johndoe@example.com',
        receiveNewsletters: true
      }
    }
  }
}
</script>

<style scoped>
.gradient-custom {
  background: #6a11cb;
  background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
  background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
}
</style>
