package com.jit.taobaounion.model.domain;

import java.util.List;

public class SelectedContent {
    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response;

        public TbkDgOptimusMaterialResponseBean getTbk_dg_optimus_material_response() {
            return tbk_dg_optimus_material_response;
        }

        public void setTbk_dg_optimus_material_response(TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response) {
            this.tbk_dg_optimus_material_response = tbk_dg_optimus_material_response;
        }

        public static class TbkDgOptimusMaterialResponseBean {
            private String is_default;
            private ResultListBean result_list;
            private int total_count;
            private String request_id;

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public ResultListBean getResult_list() {
                return result_list;
            }

            public void setResult_list(ResultListBean result_list) {
                this.result_list = result_list;
            }

            public int getTotal_count() {
                return total_count;
            }

            public void setTotal_count(int total_count) {
                this.total_count = total_count;
            }

            public String getRequest_id() {
                return request_id;
            }

            public void setRequest_id(String request_id) {
                this.request_id = request_id;
            }

            public static class ResultListBean {
                private List<MapDataBean> map_data;

                public List<MapDataBean> getMap_data() {
                    return map_data;
                }

                public void setMap_data(List<MapDataBean> map_data) {
                    this.map_data = map_data;
                }

                public static class MapDataBean implements IBaseInfo {
                    private int category_id;
                    private String click_url;
                    private String commission_rate;
                    private int coupon_amount;
                    private String coupon_click_url;
                    private String coupon_end_time;
                    private String coupon_info;
                    private int coupon_remain_count;
                    private String coupon_share_url;
                    private String coupon_start_fee;
                    private String coupon_start_time;
                    private int coupon_total_count;
                    private long item_id;
                    private int level_one_category_id;
                    private String nick;
                    private String pict_url;
                    private String reserve_price;
                    private long seller_id;
                    private String shop_title;
                    private SmallImagesBean small_images;
                    private String title;
                    private int user_type;
                    private int volume;
                    private String white_image;
                    private String zk_final_price;

                    public int getCategory_id() {
                        return category_id;
                    }

                    public void setCategory_id(int category_id) {
                        this.category_id = category_id;
                    }

                    public String getClick_url() {
                        return click_url;
                    }

                    public void setClick_url(String click_url) {
                        this.click_url = click_url;
                    }

                    public String getCommission_rate() {
                        return commission_rate;
                    }

                    public void setCommission_rate(String commission_rate) {
                        this.commission_rate = commission_rate;
                    }

                    public int getCoupon_amount() {
                        return coupon_amount;
                    }

                    public void setCoupon_amount(int coupon_amount) {
                        this.coupon_amount = coupon_amount;
                    }

                    public String getCoupon_click_url() {
                        return coupon_click_url;
                    }

                    public void setCoupon_click_url(String coupon_click_url) {
                        this.coupon_click_url = coupon_click_url;
                    }

                    public String getCoupon_end_time() {
                        return coupon_end_time;
                    }

                    public void setCoupon_end_time(String coupon_end_time) {
                        this.coupon_end_time = coupon_end_time;
                    }

                    public String getCoupon_info() {
                        return coupon_info;
                    }

                    public void setCoupon_info(String coupon_info) {
                        this.coupon_info = coupon_info;
                    }

                    public int getCoupon_remain_count() {
                        return coupon_remain_count;
                    }

                    public void setCoupon_remain_count(int coupon_remain_count) {
                        this.coupon_remain_count = coupon_remain_count;
                    }

                    public String getCoupon_share_url() {
                        return coupon_share_url;
                    }

                    public void setCoupon_share_url(String coupon_share_url) {
                        this.coupon_share_url = coupon_share_url;
                    }

                    public String getCoupon_start_fee() {
                        return coupon_start_fee;
                    }

                    public void setCoupon_start_fee(String coupon_start_fee) {
                        this.coupon_start_fee = coupon_start_fee;
                    }

                    public String getCoupon_start_time() {
                        return coupon_start_time;
                    }

                    public void setCoupon_start_time(String coupon_start_time) {
                        this.coupon_start_time = coupon_start_time;
                    }

                    public int getCoupon_total_count() {
                        return coupon_total_count;
                    }

                    public void setCoupon_total_count(int coupon_total_count) {
                        this.coupon_total_count = coupon_total_count;
                    }

                    public long getItem_id() {
                        return item_id;
                    }

                    public void setItem_id(long item_id) {
                        this.item_id = item_id;
                    }

                    public int getLevel_one_category_id() {
                        return level_one_category_id;
                    }

                    public void setLevel_one_category_id(int level_one_category_id) {
                        this.level_one_category_id = level_one_category_id;
                    }

                    public String getNick() {
                        return nick;
                    }

                    public void setNick(String nick) {
                        this.nick = nick;
                    }

                    public String getPict_url() {
                        return pict_url;
                    }

                    public void setPict_url(String pict_url) {
                        this.pict_url = pict_url;
                    }

                    public String getReserve_price() {
                        return reserve_price;
                    }

                    public void setReserve_price(String reserve_price) {
                        this.reserve_price = reserve_price;
                    }

                    public long getSeller_id() {
                        return seller_id;
                    }

                    public void setSeller_id(long seller_id) {
                        this.seller_id = seller_id;
                    }

                    public String getShop_title() {
                        return shop_title;
                    }

                    public void setShop_title(String shop_title) {
                        this.shop_title = shop_title;
                    }

                    public SmallImagesBean getSmall_images() {
                        return small_images;
                    }

                    public void setSmall_images(SmallImagesBean small_images) {
                        this.small_images = small_images;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public int getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(int user_type) {
                        this.user_type = user_type;
                    }

                    public int getVolume() {
                        return volume;
                    }

                    public void setVolume(int volume) {
                        this.volume = volume;
                    }

                    public String getWhite_image() {
                        return white_image;
                    }

                    public void setWhite_image(String white_image) {
                        this.white_image = white_image;
                    }

                    public String getZk_final_price() {
                        return zk_final_price;
                    }

                    public void setZk_final_price(String zk_final_price) {
                        this.zk_final_price = zk_final_price;
                    }

                    @Override
                    public String getCover() {
                        return pict_url;
                    }

                    @Override
                    public String getUrl() {
                        return coupon_click_url == null ? click_url :coupon_click_url;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public static class SmallImagesBean {
                        private List<String> string;

                        public List<String> getString() {
                            return string;
                        }

                        public void setString(List<String> string) {
                            this.string = string;
                        }
                    }
                }
            }
        }
    }


//    @Override
//    public String toString() {
//        return "SelectedContent{" +
//                "success=" + success +
//                ", code=" + code +
//                ", message='" + message + '\'' +
//                ", data=" + data +
//                '}';
//    }
//
//    /**
//     * success : true
//     * code : 10000
//     * message : 获取精选内容成功.
//     * data : {"tbk_uatm_favorites_item_get_response":{"results":{"favoriteId":0,"uatm_tbk_item":[{"click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DQqgvXVwF4QAcQipKwQzePOeEDrYVVa64yK8Cckff7TXjf2vlNIV67lbA7kVj6DOjxeoNewupcd6YzwUb1bbXppZwHxYkBYTUeP8T%2BYUOOiuIOBqu0asPv%2F0jCkSDmD6DKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BPxr1c6V5LsG1hjz2dNwkcRt%2ByMzJ8mpM%2Fe7B5ZkP8EjSdChf3U3iXY%2B5QowgvHJPA%3D%3D&unid=19902751&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=TBIJTibAhO4GQASttHIRqQA9fN4MifAG7tS89T6GqASOeUcSvy%2FHYmF5qFnaO9996DeX2ucVKhvHjZlrd41oqDEL25HyNw0xJ4FRquMv%2FTtFRuNP9Tp9RQH%2FlwNDrBX3XYsE5g93lTYZao0HO%2FvX0w%3D%3D&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_end_time":"2019-12-07","coupon_info":"满7元减5元","coupon_remain_count":99635,"coupon_start_time":"2019-12-01","coupon_total_count":100000,"event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"https://item.taobao.com/item.htm?id=595184094985","num_iid":595184094985,"pict_url":"https://img.alicdn.com/tfscom/i1/1744991291/O1CN01J58x6I1LPJqtM61gh_!!0-item_pic.jpg","reserve_price":"45.00","status":1,"title":"纳雷特原装正品适用 OPPO数据线闪充R9 R15 R11s R11 R11s plus R9s R7安卓手机原厂快充短充电器头线2米加长","tk_rate":"9.00","type":4,"user_type":1,"volume":11450,"zk_final_price":"10.90","zk_final_price_wap":"10.90"}]},"total_results":26,"request_id":"sa9tsfbr1uco"}}
//     */
//
//    private boolean success;
//    private int code;
//    private String message;
//    private DataBean data;
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        @Override
//        public String toString() {
//            return "DataBean{" +
//                    "tbk_uatm_favorites_item_get_response=" + tbk_uatm_favorites_item_get_response +
//                    '}';
//        }
//
//        /**
//         * tbk_uatm_favorites_item_get_response : {"results":{"favoriteId":0,"uatm_tbk_item":[{"click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DQqgvXVwF4QAcQipKwQzePOeEDrYVVa64yK8Cckff7TXjf2vlNIV67lbA7kVj6DOjxeoNewupcd6YzwUb1bbXppZwHxYkBYTUeP8T%2BYUOOiuIOBqu0asPv%2F0jCkSDmD6DKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BPxr1c6V5LsG1hjz2dNwkcRt%2ByMzJ8mpM%2Fe7B5ZkP8EjSdChf3U3iXY%2B5QowgvHJPA%3D%3D&unid=19902751&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=TBIJTibAhO4GQASttHIRqQA9fN4MifAG7tS89T6GqASOeUcSvy%2FHYmF5qFnaO9996DeX2ucVKhvHjZlrd41oqDEL25HyNw0xJ4FRquMv%2FTtFRuNP9Tp9RQH%2FlwNDrBX3XYsE5g93lTYZao0HO%2FvX0w%3D%3D&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_end_time":"2019-12-07","coupon_info":"满7元减5元","coupon_remain_count":99635,"coupon_start_time":"2019-12-01","coupon_total_count":100000,"event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"https://item.taobao.com/item.htm?id=595184094985","num_iid":595184094985,"pict_url":"https://img.alicdn.com/tfscom/i1/1744991291/O1CN01J58x6I1LPJqtM61gh_!!0-item_pic.jpg","reserve_price":"45.00","status":1,"title":"纳雷特原装正品适用 OPPO数据线闪充R9 R15 R11s R11 R11s plus R9s R7安卓手机原厂快充短充电器头线2米加长","tk_rate":"9.00","type":4,"user_type":1,"volume":11450,"zk_final_price":"10.90","zk_final_price_wap":"10.90"}]},"total_results":26,"request_id":"sa9tsfbr1uco"}
//         */
//
//        private TbkUatmFavoritesItemGetResponseBean tbk_uatm_favorites_item_get_response;
//
//        public TbkUatmFavoritesItemGetResponseBean getTbk_uatm_favorites_item_get_response() {
//            return tbk_uatm_favorites_item_get_response;
//        }
//
//        public void setTbk_uatm_favorites_item_get_response(TbkUatmFavoritesItemGetResponseBean tbk_uatm_favorites_item_get_response) {
//            this.tbk_uatm_favorites_item_get_response = tbk_uatm_favorites_item_get_response;
//        }
//
//        public static class TbkUatmFavoritesItemGetResponseBean {
//            /**
//             * results : {"favoriteId":0,"uatm_tbk_item":[{"click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DQqgvXVwF4QAcQipKwQzePOeEDrYVVa64yK8Cckff7TXjf2vlNIV67lbA7kVj6DOjxeoNewupcd6YzwUb1bbXppZwHxYkBYTUeP8T%2BYUOOiuIOBqu0asPv%2F0jCkSDmD6DKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BPxr1c6V5LsG1hjz2dNwkcRt%2ByMzJ8mpM%2Fe7B5ZkP8EjSdChf3U3iXY%2B5QowgvHJPA%3D%3D&unid=19902751&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=TBIJTibAhO4GQASttHIRqQA9fN4MifAG7tS89T6GqASOeUcSvy%2FHYmF5qFnaO9996DeX2ucVKhvHjZlrd41oqDEL25HyNw0xJ4FRquMv%2FTtFRuNP9Tp9RQH%2FlwNDrBX3XYsE5g93lTYZao0HO%2FvX0w%3D%3D&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_end_time":"2019-12-07","coupon_info":"满7元减5元","coupon_remain_count":99635,"coupon_start_time":"2019-12-01","coupon_total_count":100000,"event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"https://item.taobao.com/item.htm?id=595184094985","num_iid":595184094985,"pict_url":"https://img.alicdn.com/tfscom/i1/1744991291/O1CN01J58x6I1LPJqtM61gh_!!0-item_pic.jpg","reserve_price":"45.00","status":1,"title":"纳雷特原装正品适用 OPPO数据线闪充R9 R15 R11s R11 R11s plus R9s R7安卓手机原厂快充短充电器头线2米加长","tk_rate":"9.00","type":4,"user_type":1,"volume":11450,"zk_final_price":"10.90","zk_final_price_wap":"10.90"}]}
//             * total_results : 26
//             * request_id : sa9tsfbr1uco
//             */
//
//            private ResultsBean results;
//            private int total_results;
//            private String request_id;
//
//            public ResultsBean getResults() {
//                return results;
//            }
//
//            public void setResults(ResultsBean results) {
//                this.results = results;
//            }
//
//            public int getTotal_results() {
//                return total_results;
//            }
//
//            public void setTotal_results(int total_results) {
//                this.total_results = total_results;
//            }
//
//            public String getRequest_id() {
//                return request_id;
//            }
//
//            public void setRequest_id(String request_id) {
//                this.request_id = request_id;
//            }
//
//            public static class ResultsBean {
//                /**
//                 * favoriteId : 0
//                 * uatm_tbk_item : [{"click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DQqgvXVwF4QAcQipKwQzePOeEDrYVVa64yK8Cckff7TXjf2vlNIV67lbA7kVj6DOjxeoNewupcd6YzwUb1bbXppZwHxYkBYTUeP8T%2BYUOOiuIOBqu0asPv%2F0jCkSDmD6DKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BPxr1c6V5LsG1hjz2dNwkcRt%2ByMzJ8mpM%2Fe7B5ZkP8EjSdChf3U3iXY%2B5QowgvHJPA%3D%3D&unid=19902751&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=TBIJTibAhO4GQASttHIRqQA9fN4MifAG7tS89T6GqASOeUcSvy%2FHYmF5qFnaO9996DeX2ucVKhvHjZlrd41oqDEL25HyNw0xJ4FRquMv%2FTtFRuNP9Tp9RQH%2FlwNDrBX3XYsE5g93lTYZao0HO%2FvX0w%3D%3D&union_lens=lensId:0b012096_0c74_16ec657c76b_e230","coupon_end_time":"2019-12-07","coupon_info":"满7元减5元","coupon_remain_count":99635,"coupon_start_time":"2019-12-01","coupon_total_count":100000,"event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"https://item.taobao.com/item.htm?id=595184094985","num_iid":595184094985,"pict_url":"https://img.alicdn.com/tfscom/i1/1744991291/O1CN01J58x6I1LPJqtM61gh_!!0-item_pic.jpg","reserve_price":"45.00","status":1,"title":"纳雷特原装正品适用 OPPO数据线闪充R9 R15 R11s R11 R11s plus R9s R7安卓手机原厂快充短充电器头线2米加长","tk_rate":"9.00","type":4,"user_type":1,"volume":11450,"zk_final_price":"10.90","zk_final_price_wap":"10.90"}]
//                 */
//
//                private int favoriteId;
//                private List<UatmTbkItemBean> uatm_tbk_item;
//
//                public int getFavoriteId() {
//                    return favoriteId;
//                }
//
//                public void setFavoriteId(int favoriteId) {
//                    this.favoriteId = favoriteId;
//                }
//
//                public List<UatmTbkItemBean> getUatm_tbk_item() {
//                    return uatm_tbk_item;
//                }
//
//                public void setUatm_tbk_item(List<UatmTbkItemBean> uatm_tbk_item) {
//                    this.uatm_tbk_item = uatm_tbk_item;
//                }
//
//                public static class UatmTbkItemBean implements IBaseInfo {
//                    /**
//                     * click_url : https://s.click.taobao.com/t?e=m%3D2%26s%3DQqgvXVwF4QAcQipKwQzePOeEDrYVVa64yK8Cckff7TXjf2vlNIV67lbA7kVj6DOjxeoNewupcd6YzwUb1bbXppZwHxYkBYTUeP8T%2BYUOOiuIOBqu0asPv%2F0jCkSDmD6DKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BPxr1c6V5LsG1hjz2dNwkcRt%2ByMzJ8mpM%2Fe7B5ZkP8EjSdChf3U3iXY%2B5QowgvHJPA%3D%3D&unid=19902751&union_lens=lensId:0b012096_0c74_16ec657c76b_e230
//                     * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=TBIJTibAhO4GQASttHIRqQA9fN4MifAG7tS89T6GqASOeUcSvy%2FHYmF5qFnaO9996DeX2ucVKhvHjZlrd41oqDEL25HyNw0xJ4FRquMv%2FTtFRuNP9Tp9RQH%2FlwNDrBX3XYsE5g93lTYZao0HO%2FvX0w%3D%3D&union_lens=lensId:0b012096_0c74_16ec657c76b_e230
//                     * coupon_end_time : 2019-12-07
//                     * coupon_info : 满7元减5元
//                     * coupon_remain_count : 99635
//                     * coupon_start_time : 2019-12-01
//                     * coupon_total_count : 100000
//                     * event_end_time : 1970-01-01 00:00:00
//                     * event_start_time : 1970-01-01 00:00:00
//                     * item_url : https://item.taobao.com/item.htm?id=595184094985
//                     * num_iid : 595184094985
//                     * pict_url : https://img.alicdn.com/tfscom/i1/1744991291/O1CN01J58x6I1LPJqtM61gh_!!0-item_pic.jpg
//                     * reserve_price : 45.00
//                     * status : 1
//                     * title : 纳雷特原装正品适用 OPPO数据线闪充R9 R15 R11s R11 R11s plus R9s R7安卓手机原厂快充短充电器头线2米加长
//                     * tk_rate : 9.00
//                     * type : 4
//                     * user_type : 1
//                     * volume : 11450
//                     * zk_final_price : 10.90
//                     * zk_final_price_wap : 10.90
//                     */
//
//                    private String click_url;
//                    private String coupon_click_url;
//                    private String coupon_end_time;
//                    private String coupon_info;
//                    private int coupon_remain_count;
//                    private String coupon_start_time;
//                    private int coupon_total_count;
//                    private String event_end_time;
//                    private String event_start_time;
//                    private String item_url;
//                    private long num_iid;
//                    private String pict_url;
//                    private String reserve_price;
//                    private int status;
//                    private String title;
//                    private String tk_rate;
//                    private int type;
//                    private int user_type;
//                    private int volume;
//                    private String zk_final_price;
//                    private String zk_final_price_wap;
//
//                    public String getClick_url() {
//                        return click_url;
//                    }
//
//                    public void setClick_url(String click_url) {
//                        this.click_url = click_url;
//                    }
//
//                    public String getCoupon_click_url() {
//                        return coupon_click_url;
//                    }
//
//                    public void setCoupon_click_url(String coupon_click_url) {
//                        this.coupon_click_url = coupon_click_url;
//                    }
//
//                    public String getCoupon_end_time() {
//                        return coupon_end_time;
//                    }
//
//                    public void setCoupon_end_time(String coupon_end_time) {
//                        this.coupon_end_time = coupon_end_time;
//                    }
//
//                    public String getCoupon_info() {
//                        return coupon_info;
//                    }
//
//                    public void setCoupon_info(String coupon_info) {
//                        this.coupon_info = coupon_info;
//                    }
//
//                    public int getCoupon_remain_count() {
//                        return coupon_remain_count;
//                    }
//
//                    public void setCoupon_remain_count(int coupon_remain_count) {
//                        this.coupon_remain_count = coupon_remain_count;
//                    }
//
//                    public String getCoupon_start_time() {
//                        return coupon_start_time;
//                    }
//
//                    public void setCoupon_start_time(String coupon_start_time) {
//                        this.coupon_start_time = coupon_start_time;
//                    }
//
//                    public int getCoupon_total_count() {
//                        return coupon_total_count;
//                    }
//
//                    public void setCoupon_total_count(int coupon_total_count) {
//                        this.coupon_total_count = coupon_total_count;
//                    }
//
//                    public String getEvent_end_time() {
//                        return event_end_time;
//                    }
//
//                    public void setEvent_end_time(String event_end_time) {
//                        this.event_end_time = event_end_time;
//                    }
//
//                    public String getEvent_start_time() {
//                        return event_start_time;
//                    }
//
//                    public void setEvent_start_time(String event_start_time) {
//                        this.event_start_time = event_start_time;
//                    }
//
//                    public String getItem_url() {
//                        return item_url;
//                    }
//
//                    public void setItem_url(String item_url) {
//                        this.item_url = item_url;
//                    }
//
//                    public long getNum_iid() {
//                        return num_iid;
//                    }
//
//                    public void setNum_iid(long num_iid) {
//                        this.num_iid = num_iid;
//                    }
//
//                    public String getPict_url() {
//                        return pict_url;
//                    }
//
//                    public void setPict_url(String pict_url) {
//                        this.pict_url = pict_url;
//                    }
//
//                    public String getReserve_price() {
//                        return reserve_price;
//                    }
//
//                    public void setReserve_price(String reserve_price) {
//                        this.reserve_price = reserve_price;
//                    }
//
//                    public int getStatus() {
//                        return status;
//                    }
//
//                    public void setStatus(int status) {
//                        this.status = status;
//                    }
//
//                    @Override
//                    public String getCover() {
//                        return pict_url;
//                    }
//
//                    @Override
//                    public String getUrl() {
//                        return coupon_click_url == null ? click_url :coupon_click_url;
//                    }
//
//                    public String getTitle() {
//                        return title;
//                    }
//
//                    public void setTitle(String title) {
//                        this.title = title;
//                    }
//
//                    public String getTk_rate() {
//                        return tk_rate;
//                    }
//
//                    public void setTk_rate(String tk_rate) {
//                        this.tk_rate = tk_rate;
//                    }
//
//                    public int getType() {
//                        return type;
//                    }
//
//                    public void setType(int type) {
//                        this.type = type;
//                    }
//
//                    public int getUser_type() {
//                        return user_type;
//                    }
//
//                    public void setUser_type(int user_type) {
//                        this.user_type = user_type;
//                    }
//
//                    public int getVolume() {
//                        return volume;
//                    }
//
//                    public void setVolume(int volume) {
//                        this.volume = volume;
//                    }
//
//                    public String getZk_final_price() {
//                        return zk_final_price;
//                    }
//
//                    public void setZk_final_price(String zk_final_price) {
//                        this.zk_final_price = zk_final_price;
//                    }
//
//                    public String getZk_final_price_wap() {
//                        return zk_final_price_wap;
//                    }
//
//                    public void setZk_final_price_wap(String zk_final_price_wap) {
//                        this.zk_final_price_wap = zk_final_price_wap;
//                    }
//                }
//            }
//        }
//    }
}
