package core;

import java.util.HashMap;
import java.util.Map;

import core.interfaces.IDatabase;
import repositories.JoueursRepository;

public class Application {

    private Map<String, Object> _services;
    private Map<String, Object> _repository;

    public Application() {
        this._services = new HashMap<String, Object>();
        _services.put("database", new Database());

        this._repository = new HashMap<String, Object>();
        this._repository.put("joueursRepository", new JoueursRepository((IDatabase) this.getService("database")));
    }

    public Object getService(String reference) {
        return this._services.get(reference);
    }

    public Object getRepository(String reference) {
        return this._repository.get(reference);
    }
}
