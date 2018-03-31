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
 * Created by HQ on 2017/11/3.
 */
public class PlayInfosBean implements Bean {
    /**
     * PlayInfoList : {"PlayInfo":[{"Bitrate":"1521.0570","Definition":"SD","Duration":"205.3600","Encrypt":0,"Format":"mp4","Fps":"25.0000","Height":720,"JobId":"1f03e27328684771bc30bfeee0d189ea","PlayURL":"http://video.daoran.tv/a1fa4cabb12c4ee782858ca217fcf40d/6c3f3b3e9c5446ba9837d53b5e8f02bd-a5b7d8911cc7d347a9c9dd7e9b1d521b.mp4","Size":42491108,"StreamType":"video","Width":1280}]}
     * RequestId : F666C937-F961-4BC0-913F-2E84A49F1B5D
     * VideoBase : {"CoverURL":"http://video.daoran.tv/snapshot/a1fa4cabb12c4ee782858ca217fcf40d00001.jpg","CreationTime":"2017-07-20T23:24:43Z","Duration":"205.4","MediaType":"video","Status":"Normal","Title":"11007020000316002","VideoId":"a1fa4cabb12c4ee782858ca217fcf40d"}
     */

    private PlayInfoListBean PlayInfoList;
    private String RequestId;
    private VideoBaseBean VideoBase;

    public PlayInfoListBean getPlayInfoList() {
        return PlayInfoList;
    }

    public void setPlayInfoList(PlayInfoListBean PlayInfoList) {
        this.PlayInfoList = PlayInfoList;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    public VideoBaseBean getVideoBase() {
        return VideoBase;
    }

    public void setVideoBase(VideoBaseBean VideoBase) {
        this.VideoBase = VideoBase;
    }

    public static class PlayInfoListBean {
        private List<PlayInfoBean> PlayInfo;

        public List<PlayInfoBean> getPlayInfo() {
            return PlayInfo;
        }

        public void setPlayInfo(List<PlayInfoBean> PlayInfo) {
            this.PlayInfo = PlayInfo;
        }

        public static class PlayInfoBean {
            /**
             * Bitrate : 1521.0570
             * Definition : SD
             * Duration : 205.3600
             * Encrypt : 0
             * Format : mp4
             * Fps : 25.0000
             * Height : 720
             * JobId : 1f03e27328684771bc30bfeee0d189ea
             * PlayURL : http://video.daoran.tv/a1fa4cabb12c4ee782858ca217fcf40d/6c3f3b3e9c5446ba9837d53b5e8f02bd-a5b7d8911cc7d347a9c9dd7e9b1d521b.mp4
             * Size : 42491108
             * StreamType : video
             * Width : 1280
             */

            private String Bitrate;
            private String Definition;
            private String Duration;
            private int Encrypt;
            private String Format;
            private String Fps;
            private int Height;
            private String JobId;
            private String PlayURL;
            private int Size;
            private String StreamType;
            private int Width;

            public String getBitrate() {
                return Bitrate;
            }

            public void setBitrate(String Bitrate) {
                this.Bitrate = Bitrate;
            }

            public String getDefinition() {
                return Definition;
            }

            public void setDefinition(String Definition) {
                this.Definition = Definition;
            }

            public String getDuration() {
                return Duration;
            }

            public void setDuration(String Duration) {
                this.Duration = Duration;
            }

            public int getEncrypt() {
                return Encrypt;
            }

            public void setEncrypt(int Encrypt) {
                this.Encrypt = Encrypt;
            }

            public String getFormat() {
                return Format;
            }

            public void setFormat(String Format) {
                this.Format = Format;
            }

            public String getFps() {
                return Fps;
            }

            public void setFps(String Fps) {
                this.Fps = Fps;
            }

            public int getHeight() {
                return Height;
            }

            public void setHeight(int Height) {
                this.Height = Height;
            }

            public String getJobId() {
                return JobId;
            }

            public void setJobId(String JobId) {
                this.JobId = JobId;
            }

            public String getPlayURL() {
                return PlayURL;
            }

            public void setPlayURL(String PlayURL) {
                this.PlayURL = PlayURL;
            }

            public int getSize() {
                return Size;
            }

            public void setSize(int Size) {
                this.Size = Size;
            }

            public String getStreamType() {
                return StreamType;
            }

            public void setStreamType(String StreamType) {
                this.StreamType = StreamType;
            }

            public int getWidth() {
                return Width;
            }

            public void setWidth(int Width) {
                this.Width = Width;
            }
        }
    }

    public static class VideoBaseBean {
        /**
         * CoverURL : http://video.daoran.tv/snapshot/a1fa4cabb12c4ee782858ca217fcf40d00001.jpg
         * CreationTime : 2017-07-20T23:24:43Z
         * Duration : 205.4
         * MediaType : video
         * Status : Normal
         * Title : 11007020000316002
         * VideoId : a1fa4cabb12c4ee782858ca217fcf40d
         */

        private String CoverURL;
        private String CreationTime;
        private String Duration;
        private String MediaType;
        private String Status;
        private String Title;
        private String VideoId;

        public String getCoverURL() {
            return CoverURL;
        }

        public void setCoverURL(String CoverURL) {
            this.CoverURL = CoverURL;
        }

        public String getCreationTime() {
            return CreationTime;
        }

        public void setCreationTime(String CreationTime) {
            this.CreationTime = CreationTime;
        }

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String Duration) {
            this.Duration = Duration;
        }

        public String getMediaType() {
            return MediaType;
        }

        public void setMediaType(String MediaType) {
            this.MediaType = MediaType;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getVideoId() {
            return VideoId;
        }

        public void setVideoId(String VideoId) {
            this.VideoId = VideoId;
        }
    }
}
