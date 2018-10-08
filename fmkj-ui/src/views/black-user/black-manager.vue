<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card>
                    <p slot="title">
                        <Icon type="android-remove"></Icon>
                        黑白名单管理
                    </p>
                    <Tabs @on-click="handleClick">
                        <TabPane label="白名单" icon="ios-list-outline">
                            <div>
                                <RaceBlack :isLoad="true" ref="RaceBlack"></RaceBlack>
                            </div>
                        </TabPane>
                        <TabPane label="黑名单" icon="ios-locked">
                            <div>
                                <RaceBlack :isLoad="false" ref="RaceBlack"></RaceBlack>
                            </div>
                        </TabPane>
                    </Tabs>
                </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import RaceBlack from './race-black.vue';
    export default {
        components: {
            RaceBlack
        },
        methods: {
            handleClick (index) {
                // index 是TabPane的下标位置
                this.$refs.RaceBlack.getData(1, index);
            }
        }
    };
</script>
