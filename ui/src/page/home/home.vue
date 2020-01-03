<template>
    <div class="home">
        <!--时间-->
        <div style="float: right;padding-left: 50px;">
            <spn>当前时间：{{nowTime}}</spn>
        </div>
    </div>
</template>

<script>
    import http from '../../axios/http';
    import Clock from 'vue-clock2';

    export default {
        components: { Clock },
        name: 'home',
        data() {
            return {
                myTime: "",
                nowTime:"",
                info:{}
            }
        },
        methods: {

        },

        mounted() {

        }
    }
</script>

<style scoped>
    .home{
        padding-left:20px;
        padding-right:20px;
    }
</style>

