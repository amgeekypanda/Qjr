package geekypanda.com.qjr.Constant;

/**
 * Created by SRIDHAR on 28-02-2015.
 */
public class AIE_C_URL {
   // private static String hostname = "http://localhost:3000";
   private static String hostname = "http://sample-env.eunbs6wxr6.ap-south-1.elasticbeanstalk.com";
    private static String hostnamedummy = "http://188.166.238.35:3000";
    private static String hostname1 = "http://188.166.238.35:3000";
    private static String imghostname = "http://188.166.238.35:3000";
    private static String imgSize = "-270x430";
    private static String smimgSize = "-100x100";
    private static String menuUrl = "/api/v1/sidemenu/";
    private static String accessToken = "?access_token=";
    private String loginUrl = "/api/v1/login";

    private String logoutUrl = "/api/v1/logout";
    private String settingsUrl = "/api/v1/setting";
    private String settingsUrl_Key = "/api/v1/setting/AIE_splash";
    private String settingsUrl_Key_Status = "/api/v1/setting/AIE_splash/1";
    private String regIdServerUrl = "/api/v1/gcmregister";
    private String countryUrl = "http://jamhubsoftware.com/geoip/geoip.php";
    private String categoryUrl = "/api/v1/store/";
    private String categoryStoreUrl = "/api/v1/store/";
    private String custAddress = "/api/v1/customer";
    private String allProductUrl = "/api/v1/product";
    private String Product = "/api/v1/store/";
    private String allProductDiscUrl = "/api/v1/product/discount";
    private String customerUrl = "/api/v1/customer";
    private String customeridUrl = "/api/v1/customer";
    private String fedbackUrl = "/api/v1/feedback";
    private String locationUrl = "/branches";
    private String categorytosubcat = "/api/v1/category/";
    private String getVerfctn_email = "/api/v1/sendmail";
    private String middleUrl = "/api/v1/middle/images/";
    private String taxDetails = "/api/v1/order/store/";
    private String orderupdate = "/api/v1/order";
    private String locationlink = "http://www.jamhubsoftware.com/chennaimobile/json_storelocation.php";
    private String store = "/api/v1/store";
    private String city = "/api/v1/city";
    private String order = "/order";
    private String orderhistory = "/api/v1/customer/";
    private String orderhistorydetails = "/api/v1/order/";
    private String bannerurl_storeid="/api/v1/banner/store/";
    private String offerUrl = "/api/v1/banner/class/AIE_M_OFFER";
    private String homeBannerUrl1 = "/api/v1/banner/class/FIRST_ROW";
    private String homeBannerUrl2 = "/api/v1/banner/class/SECOND_ROW";
    private String storeLocation = "/api/v1/store/location";
    private String orderStatus = "/status";
    private String discountCoupon = "/api/v1/coupon";
    private String current_timing = "/api/v1/currenttime";
    private String store_timing = "/timing";
    private String version = "/version";
    private String deliveryarea = "/deliveryarea";
    private String store_url = "/api/v1/store";
    private String orderProduct = "/api/v1/order";
    private String deleteAddress = "/api/v1/customer";
    private String loyalty_insertUrl = "http://loyaltyprograms.in/nalasappws/mobile/registerCustomer?applicationId=MB";
    private String loyalty_fetchUrl = "http://loyaltyprograms.in/nalasappws/mobile/getCustomerDetails?applicationId=MB";
    private String layalty_updateUrl = "http://loyaltyprograms.in/nalasappws/mobile/insertTransaction?applicationId=MB";
    private String customernew = "/customer/mobile";

    public String getCustomernew() {
        return customernew;
    }

    public void setCustomernew(String customernew) {
        this.customernew = customernew;
    }

    public static String getHostnamedummy() {
        return hostnamedummy;
    }

    public static void setHostnamedummy(String hostnamedummy) {
        AIE_C_URL.hostnamedummy = hostnamedummy;
    }

    public static String getmenuUrl() {
        return menuUrl;
    }

    public static String getImgSize() {
        return imgSize;
    }

    public static void setImgSize(String imgSize) {
        AIE_C_URL.imgSize = imgSize;
    }

    public static String getSmimgSize() {
        return smimgSize;
    }

    public static void setSmimgSize(String smimgSize) {
        AIE_C_URL.smimgSize = smimgSize;
    }

    public static String getImghostname() {
        return imghostname;
    }

    public static void setImghostname(String imghostname) {
        AIE_C_URL.imghostname = imghostname;
    }

    public static String getaccessToken() {
        return accessToken;
    }

    public static String getHostname() {
        return hostname;
    }

    public static void setHostname(String hostname) {
        AIE_C_URL.hostname = hostname;
    }

    public static String getHostname1() {
        return hostname1;
    }

    public static void setHostname1(String hostname1) {
        AIE_C_URL.hostname1 = hostname1;
    }

    public String getDeleteAddress() {
        return deleteAddress;
    }

    public void setDeleteAddress(String deleteAddress) {
        this.deleteAddress = deleteAddress;
    }

    public String getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(String orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getCategorytosubcat() {
        return categorytosubcat;
    }

    public void setCategorytosubcat(String categorytosubcat) {
        this.categorytosubcat = categorytosubcat;
    }

    public String getBannerurl_storeid() {
        return bannerurl_storeid;
    }

    public void setBannerurl_storeid(String bannerurl_storeid) {
        this.bannerurl_storeid = bannerurl_storeid;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public String getStore_url() {
        return store_url;
    }

    public void setStore_url(String store_url) {
        this.store_url = store_url;
    }

    public String getDeliveryarea() {
        return deliveryarea;
    }

    public void setDeliveryarea(String deliveryarea) {
        this.deliveryarea = deliveryarea;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCurrent_timing() {
        return current_timing;
    }

    public void setCurrent_timing(String current_timing) {
        this.current_timing = current_timing;
    }

    public String getStore_timing() {
        return store_timing;
    }

    public void setStore_timing(String store_timing) {
        this.store_timing = store_timing;
    }

    public String getLoyalty_fetchUrl() {
        return loyalty_fetchUrl;
    }

    public void setLoyalty_fetchUrl(String loyalty_fetchUrl) {
        this.loyalty_fetchUrl = loyalty_fetchUrl;
    }

    public String getLayalty_updateUrl() {
        return layalty_updateUrl;
    }

    public void setLayalty_updateUrl(String layalty_updateUrl) {
        this.layalty_updateUrl = layalty_updateUrl;
    }

    public String getLoyalty_insertUrl() {
        return loyalty_insertUrl;
    }

    public void setLoyalty_insertUrl(String loyalty_insertUrl) {
        this.loyalty_insertUrl = loyalty_insertUrl;
    }

    public String getOrderupdate() {
        return orderupdate;
    }

    public void setOrderupdate(String orderupdate) {
        this.orderupdate = orderupdate;
    }

    public String getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(String discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    // api/v1/customer/9378/address/10548

    public void setOfferUrl(String offerUrl) {
        this.offerUrl = offerUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCategoryStoreUrl() {
        return categoryStoreUrl;
    }

    public void setCategoryStoreUrl(String categoryStoreUrl) {
        this.categoryStoreUrl = categoryStoreUrl;
    }

    public String getGetVerfctn_email() {
        return getVerfctn_email;
    }

    public void setGetVerfctn_email(String getVerfctn_email) {
        this.getVerfctn_email = getVerfctn_email;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getMiddleUrl() {
        return middleUrl;
    }

    public void setMiddleUrl(String middleUrl) {
        this.middleUrl = middleUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFedbackUrl() {
        return fedbackUrl;
    }

    public void setFedbackUrl(String fedbackUrl) {
        this.fedbackUrl = fedbackUrl;
    }

    public String getVerfctn_email() {
        return getVerfctn_email;
    }

    public void getVerfctn_email(String verfctnemai) {
        this.getVerfctn_email = verfctnemai;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    public String getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(String taxDetails) {
        this.taxDetails = taxDetails;
    }

    public String getAllProductDiscUrl() {
        return allProductDiscUrl;
    }

    public void setAllProductDiscUrl(String allProductDiscUrl) {
        this.allProductDiscUrl = allProductDiscUrl;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getAllProductUrl() {
        return allProductUrl;
    }

    public void setAllProductUrl(String allProductUrl) {
        this.allProductUrl = allProductUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getSettingsUrl() {
        return settingsUrl;
    }

    public void setSettingsUrl(String settingsUrl) {
        this.settingsUrl = settingsUrl;
    }

    public String getSettingsUrl_Key() {
        return settingsUrl_Key;
    }

    public void setSettingsUrl_Key(String settingsUrl_Key) {
        this.settingsUrl_Key = settingsUrl_Key;
    }

    public String getSettingsUrl_Key_Status() {
        return settingsUrl_Key_Status;
    }

    public void setSettingsUrl_Key_Status(String settingsUrl_Key_Status) {
        this.settingsUrl_Key_Status = settingsUrl_Key_Status;
    }

    public String getRegIdServerUrl() {
        return regIdServerUrl;
    }

    public void setRegIdServerUrl(String regIdServerUrl) {
        this.regIdServerUrl = regIdServerUrl;
    }

    public String getCountryUrl() {
        return countryUrl;
    }

    public void setCountryUrl(String countryUrl) {
        this.countryUrl = countryUrl;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public String getLocationlink() {
        return locationlink;
    }

    public void setLocationlink(String locationlink) {
        this.locationlink = locationlink;
    }

    public String getHomeBannerUrl1() {
        return homeBannerUrl1;
    }

    public void setHomeBannerUrl1(String homeBannerUrl1) {
        this.homeBannerUrl1 = homeBannerUrl1;
    }

    public String getHomeBannerUrl2() {
        return homeBannerUrl2;
    }

    public void setHomeBannerUrl2(String homeBannerUrl2) {
        this.homeBannerUrl2 = homeBannerUrl2;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getOrderhistory() {
        return orderhistory;
    }

    public void setOrderhistory(String orderhistory) {
        this.orderhistory = orderhistory;
    }

    public String getOrderhistorydetails() {
        return orderhistorydetails;
    }

    public void setOrderhistorydetails(String orderhistorydetails) {
        this.orderhistorydetails = orderhistorydetails;
    }

    public String getCustomeridUrl() {
        return customeridUrl;
    }

    public void setCustomeridUrl(String customeridUrl) {
        this.customeridUrl = customeridUrl;
    }
}
