<template>
    <div class="set_nickname">
        <appbar titleText="修改昵称"></appbar>
        <van-cell-group>
            <van-field v-model="nickName" label="昵称"/>
        </van-cell-group>

        <div class="bottom_btn">
            <van-button size="large" type="danger" @click="saveNick">保存</van-button>
        </div>
    </div>
</template>


<script>
    import {authProfile} from '@/api/api';
    import {Field} from 'vant';
    import appbar from '@/components/head/appbar'

    export default {
        data() {
            return {
                nickName: ''
            };
        },

        created() {
            this.getNick();
        },

        methods: {
            getNick() {
                this.nickName = localStorage.getItem('nickName') || '';
            },
            saveNick() {
                if (true) {
                    authProfile({nickName: this.nickName})
                        .then(res => {
                            if (res.status == 200) {
                                if (res.data.errno == 0) {
                                    localStorage.setItem('nickName', this.nickName);
                                    return this.$dialog.alert({message: '保存成功'});
                                }
                            }
                        })
                        .then(() => {
                            this.$router.go(-1);
                        });
                }
            }
        },

        components: {
            appbar,
            [Field.name]: Field
        }
    };
</script>


<style scoped>
    .bottom_btn {
        padding: 30px 15px 0 15px;
    }
</style>
