<template>
    <transition name="fade">
        <div class="vue-modal" v-show="open">
            <transition name="drop-in">
                <div class="vue-modal-inner" v-show="open">
                    <div class="vue-modal-content">
                        <slot></slot>
                        <p>
                            <button class="btn btn-dark" @click="closeModal" style="margin-top: 5%;">Close</button>
                        </p>
                    </div>
                </div>
            </transition>
        </div>
    </transition>
</template>

<script>
export default{
    props: {
        open: {
            type: Boolean,
            required: true
        }
    },
    methods: {
        closeModal() {
            this.$emit('close');
        }
    }
}
</script>

<style scoped>
*,

::before,
::after{
    margin:0;
    padding: 0;
    box-sizing: border-box;
}

.vue-modal{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
    background-color: rgba(0,0,0,0.4);
    z-index: 1;
}

.vue-modal-inner{
    max-width: 500px;
    margin: 2rem auto;
}

.vue-modal-content{
    position: relative;
    background-color: #273036;
    border: 1px solid rgba(0,0,0,0.3);
    background-clip: padding-box;
    border-radius: 0.3rem;
    padding: 1rem;
}

.fade-enter-active, .fade-leave-active{
    transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to{
    opacity: 0;
}

.drop-in-enter-active, .drop-in-leave-active{
    transition: all 10s ease-out;
}

.drop-in-enter-form, .drop-in-leave-to{
    opacity: 0;
    transform: translateY(-50px);
}

</style>