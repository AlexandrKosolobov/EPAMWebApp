package by.kosolobov.bshop.entity;

public class Service {
    private final int serviceId;
    private final String serviceName;

    public Service(int serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }
}
