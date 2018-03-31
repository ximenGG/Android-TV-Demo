package com.iptv.hq.bean;

import java.util.List;

/**
 * 　　    ()  　　  ()
 * 　　   ( ) 　　　( )
 * 　　   ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Created by HQ on 2017/10/18.
 */
public class KuLeBean implements Bean {

    /**
     * pageId : ad_lxyy_klok
     * type : 1
     * bgImage :
     * notice :
     * video : null
     * dynrecs : []
     * layrecs : [{"partType":"layrec","eleType":"plist","resType":1,"eleValue":"lt_lx_lgfc","layoutSeq":1,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/B/C/lgxc_gao_HXSI.jpg","imageVB":null,"imageVC":null},{"partType":"layrec","eleType":"plist","resType":1,"eleValue":"lx_2_1_jfth","layoutSeq":2,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/A/D/jfth_gao_E2L0.jpg","imageVB":null,"imageVC":null},{"partType":"layrec","eleType":"plist","resType":1,"eleValue":"cgbb2","layoutSeq":3,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/D/C/cgbb_gao_1HJX.jpg","imageVB":null,"imageVC":null},{"partType":"layrec","eleType":"plist","resType":1,"eleValue":"lx_2_1_cdxx","layoutSeq":4,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/D/D/cdxx_gao_TMXA.jpg","imageVB":null,"imageVC":null},{"partType":"layrec","eleType":"plist","resType":1,"eleValue":"lx_2_1_jqcs","layoutSeq":5,"imageVA":"OPERATE/ELEMENT_IMAGE/plist/B/C/jqcs_gao_S8FX.jpg","imageVB":null,"imageVC":null}]
     * pagerecs : [{"partType":"pagerec","eleType":"vlist","resType":1,"eleValue":"lxyy_dbphb","layoutSeq":1,"imageVA":"OPERATE/ELEMENT_IMAGE/vlist/A/D/dbph_gao_HX67.png","imageVB":null,"imageVC":null},{"partType":"pagerec","eleType":"vlist","resType":1,"eleValue":"lxyy_zxsh","layoutSeq":2,"imageVA":"OPERATE/ELEMENT_IMAGE/vlist/C/D/zxsx_gao_RP3C.png","imageVB":null,"imageVC":null}]
     */

    private String pageId;
    private int type;
    private String bgImage;
    private String notice;
    private Object video;
    private List<?> dynrecs;
    private List<LayrecsBean> layrecs;
    private List<PagerecsBean> pagerecs;

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

    public List<PagerecsBean> getPagerecs() {
        return pagerecs;
    }

    public void setPagerecs(List<PagerecsBean> pagerecs) {
        this.pagerecs = pagerecs;
    }

    public static class LayrecsBean {
        /**
         * partType : layrec
         * eleType : plist
         * resType : 1
         * eleValue : lt_lx_lgfc
         * layoutSeq : 1
         * imageVA : OPERATE/ELEMENT_IMAGE/plist/B/C/lgxc_gao_HXSI.jpg
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

    public static class PagerecsBean {
        /**
         * partType : pagerec
         * eleType : vlist
         * resType : 1
         * eleValue : lxyy_dbphb
         * layoutSeq : 1
         * imageVA : OPERATE/ELEMENT_IMAGE/vlist/A/D/dbph_gao_HX67.png
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
