<template>
    <div class="col-4">
        <div class="card shadow-sm">
        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

        <div class="card-body">
            <p class="card-text">{{content}}</p>
            <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
                <button type="button" class="btn btn-sm btn-outline-secondary">{{username}}</button>
            </div>
            <small class="text-muted">{{time}}</small>
            </div>
        </div>
        </div>
    </div>
</template>

<script>

export default {
  name: "Card",
  props: {
      content: String,
      time: String,
      username: String
  }
};
</script>

<style>
</style>
