package by.kosolobov.bshop.entity;

public class Service {
    private final int serviceId;
    private final String serviceName;
    private final int servicePrice;

    public Service(int serviceId, String serviceName, int servicePrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getServicePrice() {
        return servicePrice;
    }
}
