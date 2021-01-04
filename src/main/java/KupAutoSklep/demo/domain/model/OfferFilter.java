package KupAutoSklep.demo.domain.model;

public class OfferFilter {
   private Integer manufacturerId;
    private Integer modelId;
    private Integer fromYear;
    private Integer toYear;
    private Integer page;
    private String sortBy;
    private String order;
    private Integer pageSize;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        this.toYear = toYear;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
