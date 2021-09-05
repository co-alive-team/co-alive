<template>
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">co-alive</h1>
        <p class="lead text-muted">혼자 사는데 백신 접종 때문에 불안하다면, 비상연락처를 등록하세요.</p>
        <p>
          <a href="#" class="btn btn-primary my-2">비상연락처 등록하기</a>
        </p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "Section",
  props: {
    title: String,
  },
};
</script>

<style scoped>
</style>
