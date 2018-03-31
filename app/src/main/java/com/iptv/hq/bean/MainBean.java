package com.iptv.hq.bean;


import java.util.List;

/**
* Created by HQ on 2017/10/17
*/

public class MainBean implements Bean {

    /**
     * pageId : ad_lxyy_home_2_3
     * type : 1
     * bgImage :
     * notice :
     * video : null
     * dynrecs : []
     * layrecs : [{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_klok","layoutSeq":1,"imageVA":"OPERATE/ELEMENT_IMAGE/page/D/C/KTV0803gao_L72S.jpg","imageVB":null,"imageVC":null},{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_MP3","layoutSeq":2,"imageVA":"OPERATE/ELEMENT_IMAGE/page/B/D/MP30803gao_WMWX.jpg","imageVB":null,"imageVC":null},{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_STORE","layoutSeq":3,"imageVA":"OPERATE/ELEMENT_IMAGE/page/C/D/store_1ODP.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_HISTORY","layoutSeq":4,"imageVA":"OPERATE/ELEMENT_IMAGE/page/B/D/history_TO5L.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_rmgs","layoutSeq":5,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/A/A/rmgs_BGMA.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_zjl","layoutSeq":6,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/C/C/zjl_U2GT.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_zj","layoutSeq":7,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/B/A/zj_CM9P.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_tly_PA","layoutSeq":8,"imageVA":"OPERATE/ELEMENT_IMAGE/page/D/A/tly_C9OR.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_gcw","layoutSeq":9,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/B/A/gcw_Q8R6.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_xzy","layoutSeq":10,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/A/C/xzy_6MWU.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_xqh","layoutSeq":11,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/D/B/xqh_FGLV.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_jqb_PA","layoutSeq":12,"imageVA":"OPERATE/ELEMENT_IMAGE/page/A/C/jqb_UHQU.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"page","resType":0,"eleValue":"ad_lxyy_ndx_PA","layoutSeq":13,"imageVA":"OPERATE/ELEMENT_IMAGE/page/A/D/ndx_PM8V.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_rbMV","layoutSeq":14,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/A/C/rbMV_8ZRH.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_hydhns","layoutSeq":15,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/C/B/xzq_U9FK.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_yqmjh","layoutSeq":16,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/A/C/ztj_5ATD.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":0,"eleValue":"ad_lxyy_msdsjd","layoutSeq":17,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/C/A/kjgw_8FOH.jpg","imageVB":"","imageVC":null},{"partType":"layrec","eleType":"plist","resType":1,"eleValue":"xs_sy_ott","layoutSeq":18,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/C/A/new_ZHAK.jpg","imageVB":null,"imageVC":null}]
     * pagerecs : []
     */

    private String pageId;
    private int type;
    private String bgImage;
    private String notice;
    private Object video;
    private List<?> dynrecs;
    private List<LayrecsBean> layrecs;
    private List<?> pagerecs;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public List<?> getDynrecs() {
        return dynrecs;
    }

    public void setDynrecs(List<?> dynrecs) {
        this.dynrecs = dynrecs;
    }

    public List<LayrecsBean> getLayrecs() {
        return layrecs;
    }

    public void setLayrecs(List<LayrecsBean> layrecs) {
        this.layrecs = layrecs;
    }

    public List<?> getPagerecs() {
        return pagerecs;
    }

    public void setPagerecs(List<?> pagerecs) {
        this.pagerecs = pagerecs;
    }

    public static class LayrecsBean {
        /**
         * partType : layrec
         * eleType : page
         * resType : 0
         * eleValue : ad_lxyy_klok
         * layoutSeq : 1
         * imageVA : OPERATE/ELEMENT_IMAGE/page/D/C/KTV0803gao_L72S.jpg
         * imageVB : null
         * imageVC : null
         */

        private String partType;
        private String eleType;
        private int resType;
        private String eleValue;
        private int layoutSeq;
        private String imageVA;
        private Object imageVB;
        private Object imageVC;

        public String getPartType() {
            return partType;
        }

        public void setPartType(String partType) {
            this.partType = partType;
        }

        public String getEleType() {
            return eleType;
        }

        public void setEleType(String eleType) {
            this.eleType = eleType;
        }

        public int getResType() {
            return resType;
        }

        public void setResType(int resType) {
            this.resType = resType;
        }

        public String getEleValue() {
            return eleValue;
        }

        public void setEleValue(String eleValue) {
            this.eleValue = eleValue;
        }

        public int getLayoutSeq() {
            return layoutSeq;
        }

        public void setLayoutSeq(int layoutSeq) {
            this.layoutSeq = layoutSeq;
        }

        public String getImageVA() {
            return imageVA;
        }

        public void setImageVA(String imageVA) {
            this.imageVA = imageVA;
        }

        public Object getImageVB() {
            return imageVB;
        }

        public void setImageVB(Object imageVB) {
            this.imageVB = imageVB;
        }

        public Object getImageVC() {
            return imageVC;
        }

        public void setImageVC(Object imageVC) {
            this.imageVC = imageVC;
        }
    }
}