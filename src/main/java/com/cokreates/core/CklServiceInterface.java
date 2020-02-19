package com.cokreates.core;

import java.util.List;

public interface CklServiceInterface<Dto extends MasterDTO,Entity extends BaseEntity>{

    public Entity create(Dto dto);

    public List<Entity> createAll (List<Dto> dtos);

    public boolean isValid(List<Dto> dto);

    public Entity update(Dto dto);

    public List<Entity> updateAll(List<Dto> dtos);

    public Dto delete(String oid);

    public List<Dto> deleteAll(List<String> oids);

    public Dto get(String oid);

    public List<Dto> getSelected(List<String> oids);

    public List<Dto> getList();

    public Dto convertToDto(Entity entity);


}
