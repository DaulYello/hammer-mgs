<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card>
                    <p slot="title">
                        <Icon type="android-remove"></Icon>
                        用户信息
                    </p>
                    <Tabs @on-click="handleBack">
                        <TabPane label="用户名单" icon="android-person">
                            <div>
                                <UserInfo :isLoad="true" ref="UserInfo"></UserInfo>
                            </div>
                        </TabPane>
                        <TabPane label="禁止登录名单" icon="android-lock">
                            <div>
                                <UserDrack :isLoad="false" ref="UserDrack"></UserDrack>
                            </div>
                        </TabPane>
                    </Tabs>
                </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import UserInfo from './user-info.vue';
    import UserDrack from './user-drack.vue';
    export default {
        name: 'UserManager',
        components: {
            UserInfo,
            UserDrack
        },
        methods: {
            handleBack (index) {
                //index 是TabPane的下标位置,0:用户名单；1: 黑名单
                if (index === 0) {
                    this.$refs.UserInfo.getData(1, index);
                }
                if (index === 1) {
                    this.$refs.UserDrack.getData(1, index);
                }
            }
        }
    };
</script>
