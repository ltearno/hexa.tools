package fr.lteconsulting.hexa.revrpc.server;

import java.util.HashMap;

import fr.lteconsulting.hexa.revrpc.server.CallSerializer.SerializedCallReceiver;

public class RevRPCServices {
    HashMap<String, RevRPCService> services = new HashMap<String, RevRPCService>();
    private Callback callback;

    public RevRPCServices(Callback callback) {
        this.callback = callback;
    }

    public <T> T queryInterface(String serviceName, Class<T> serviceInterface) {
        RevRPCService svc = services.get(serviceName);
        if (svc == null) {
            svc = new RevRPCService(serviceName);
            services.put(serviceName, svc);
        }

        T proxy = svc.serializer.queryBroadcastInterface(serviceInterface);

        return proxy;
    }

    public interface Callback {
        void newCall(JSONObject json);
    }

    class RevRPCService {
        String serviceName;
        CallSerializer serializer = new CallSerializer(new SerializedCallReceiver() {
            public void newCall(Class<?> proxiedInterface, JSONObject obj) {
                JSONObject serviceCall = new JSONObject();
                serviceCall.put("service", serviceName);
                serviceCall.put("interface", proxiedInterface.getCanonicalName());
                serviceCall.put("value", obj);

                callback.newCall(serviceCall);
            }
        });

        public RevRPCService(String serviceName) {
            this.serviceName = serviceName;
        }
    }
}
