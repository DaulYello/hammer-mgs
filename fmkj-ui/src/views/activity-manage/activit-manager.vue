<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card>
                    <p slot="title">
                        <Icon type="android-remove"></Icon>
                        活动管理
                    </p>
                    <Tabs @on-click="handleBack">
                        <TabPane label="未审核" icon="android-list">
                            <div>
                                <AuditAcitivity :isLoad="true" ref="AuditAcitivity"></AuditAcitivity>
                            </div>
                        </TabPane>
                        <TabPane label="已上线" icon="android-done">
                            <div>
                                <OnlineAcitivity :isLoad="false" ref="OnlineAcitivity"></OnlineAcitivity>
                            </div>
                        </TabPane>
                        <TabPane label="已下线" icon="android-remove">
                            <div>
                                <OfflineAcitivity :isLoad="false" ref="OfflineAcitivity"></OfflineAcitivity>
                            </div>
                        </TabPane>
                        <TabPane label="审核未通过" icon="android-drafts">
                            <div>
                                <BrowAcitivity :isLoad="false" ref="BrowAcitivity"></BrowAcitivity>
                            </div>
                        </TabPane>
                    </Tabs>
                </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import AuditAcitivity from './autit-activity.vue';
    import OnlineAcitivity from './online-activity.vue';
    import BrowAcitivity from './brow-activity.vue';
    import OfflineAcitivity from './offline-activity.vue';
    export default {
        components: {
            AuditAcitivity,
            OnlineAcitivity,
            BrowAcitivity,
            OfflineAcitivity
        },
        methods: {
            handleBack (index) {
                //index 是TabPane的下标位置,从0开始、第二个参数代表页签的状态值
                if (index === 0) {
                    this.$refs.AuditAcitivity.getData(1, index);
                }
                if (index === 1) {
                    this.$refs.OnlineAcitivity.getData(1, index);
                }
                if (index === 2) {
                    this.$refs.OfflineAcitivity.getData(1, index);
                }
                if (index === 3) {
                    this.$refs.BrowAcitivity.getData(1, index);
                }

            }
        }
    };
</script>
