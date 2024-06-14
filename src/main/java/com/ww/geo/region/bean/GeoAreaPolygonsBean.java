package com.ww.geo.region.bean;


import java.util.List;

public class GeoAreaPolygonsBean {
    private Short id;
    private Integer cityId;
    private String name;
    private String geos;
    private List<String> geoPolygons;

    private GeoAreaPolygonsBean parent;
    private List<GeoAreaPolygonsBean> sub;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeos() {
        return geos;
    }

    public void setGeos(String geos) {
        this.geos = geos;
    }

    public List<String> getGeoPolygons() {
        return geoPolygons;
    }

    public void setGeoPolygons(List<String> geoPolygons) {
        this.geoPolygons = geoPolygons;
    }

    public GeoAreaPolygonsBean getParent() {
        return parent;
    }

    public void setParent(GeoAreaPolygonsBean parent) {
        this.parent = parent;
    }

    public List<GeoAreaPolygonsBean> getSub() {
        return sub;
    }

    public void setSub(List<GeoAreaPolygonsBean> sub) {
        this.sub = sub;
    }
}