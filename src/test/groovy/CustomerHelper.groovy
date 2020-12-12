class CustomerHelper {

    static def aCustomerWithRankAndAddress(CustomerRank rank, Address address) {
        new Customer("山田", "太郎", address, rank)
    }

    static def aRegularCustomerFromMainland() {
        new Customer("山田", "太郎", anAddressInMainland(), CustomerRank.REGULAR)
    }

    static def aRegularCustomerFromRemoteIsland() {
        new Customer("山田", "太郎", anAddressInRemoteIsland(), CustomerRank.REGULAR)
    }

    static def aPremiumCustomerFromMainland() {
        new Customer("山田", "太郎", anAddressInMainland(), CustomerRank.PREMIUM)
    }

    static def aPremiumCustomerFromRemoteIsland() {
        new Customer("山田", "太郎", anAddressInRemoteIsland(), CustomerRank.PREMIUM)
    }

    static def anAddressInMainland() {
        // 品川駅
        new Address("108-0074", "東京都", "港区", "高輪3-26-27")
    }

    static def anAddressInRemoteIsland() {
        // 那覇空港
        new Address("901-0142", "沖縄県", "那覇市", "鏡水150")
    }
}
