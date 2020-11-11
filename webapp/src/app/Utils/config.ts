class ConfigURLApi{

    //Root 
    restServiceRoot:string;
    orderRestService:string;
    productRestService:string;
    userRestService:string;

    //Order
    createOrder:string;
    getOrderById:(id:string) => string;
    getOrdersByBuyer:(id:string) => string;
    getOrdersBySeller:(id:string) => string;
    getAllOrders:string;
    deleteOrderById:(id:number) => string;
    newReview:(id:string) => string;

    //Product
    createProduct:string;
    updateProduct:string;
    getProductById:(id: string) => string;
    getAllProducts:string;
    deleteProductById:(id: string) => string;
    getAllProductsByBrand:(band: string) => string;
    getProductsByRangeOfPrice: string;

    //User
    login:string

    //Address
    createAddress: (userId:number) => string;
    updateAddress:string;
    getAddressById:(id: number) => string;
    deleteAddressById:(id: number) => string;

    //Buyer
    createBuyer:string;
    getBuyerById:(id: number) => string;
    getAllBuyers:string;
    updateBuyer:string;
    deleteBuyerById:(id: number) => string;


    //Seller
    createSeller:string;
    getSellerById:(id:number) => string;
    getAllSellers:string;
    updateSeller:string;
    deleteSellerById:(id:number) => string;

    constructor(){
        /**
         * Le consctructeur va attribuer toutes les URLs.
         * Utiliser le constructeur est obligatoire dans ce cas
         * car on attribut les URLs de façon dynamique.
         * #### Exemple
         *  `${this.attribut}/uri` 
         */

         //URL API Gateway 
        this.restServiceRoot= `http://localhost:9090`;

        //URL des microservices
        this.orderRestService = `${this.restServiceRoot}/marketplace-order`;
        this.productRestService = `${this.restServiceRoot}/marketplace-product`;
        this.userRestService = `${this.restServiceRoot}/marketplace-user`;

        //URL du controleur order
         this.createOrder = `${this.orderRestService}/order/create`;
         this.getOrderById = ((id:string) => `${this.orderRestService}/order/${id}`);
         this.getOrdersByBuyer = ((id:string) => `${this.orderRestService}/order/buyer/${id}`);
         this.getOrdersBySeller = ((id:string) => `${this.orderRestService}/order/seller${id}`);
         this.getAllOrders = `${this.orderRestService}/order/all`;
         this.deleteOrderById = ((id:number) => `${this.orderRestService}/order/delete/${id}`);
         this.newReview = ((id: string) => `${this.orderRestService}/${id}/newreview`);

         //URL du controler User
         this.login = `${this.userRestService}/user/login`;
         //Buyer
         this.createBuyer= `${this.userRestService}/buyer/register`;
         this.updateBuyer= `${this.userRestService}/buyer/update`;
         this.getBuyerById= (id:number) => `${this.userRestService}/buyer/${id}`;
         this.getAllBuyers= `${this.userRestService}/buyer/all`;
         this.deleteBuyerById= (id:number) => `${this.userRestService}/buyer/${id}`;
         //Seller
         this.createSeller= `${this.userRestService}/seller/create`;
         this.updateSeller= `${this.userRestService}/seller/update`;
         this.getSellerById= (id: number) => `${this.userRestService}/seller/${id}`;
         this.getAllSellers= `${this.userRestService}/seller/all`;
         this.deleteBuyerById= (id: number) => `${this.userRestService}/seller/delete/${id}`;
         //Address
         this.createAddress= (userId: number) => `${this.userRestService}/address/${userId}/create`
         this.updateAddress= `${this.userRestService}/address/update`
         this.getAddressById= (id: number) => `${this.userRestService}/address/${id}`
         this.deleteBuyerById= (id:number) => `${this.userRestService}/address/${id}`

         //URL du controler Product
         this.createProduct= `${this.productRestService}/product/create`
         this.updateProduct= `${this.productRestService}/product/update`
         this.getProductById= (id: string) => `${this.productRestService}/product/${id}`
         this.getAllProducts= `${this.productRestService}/product/All`
         this.deleteProductById= (id: string) => `${this.productRestService}/product/delete/${id}`
         this.getAllProductsByBrand= (brand: string) => `${this.productRestService}/product/${brand}`
         this.getProductsByRangeOfPrice= `${this.productRestService}/product/byprice`
    }

};
export const configURLApi : ConfigURLApi = new ConfigURLApi();