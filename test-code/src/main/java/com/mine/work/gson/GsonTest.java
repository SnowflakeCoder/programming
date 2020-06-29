package com.mine.work.gson;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public static void main(String[] args) {
//		Gson gson = new Gson();
        
		GsonBuilder builder = new GsonBuilder();
		builder.enableComplexMapKeySerialization();
		Gson gson = builder.create();
		
		Type collectionType = new TypeToken<PostItem>(){}.getType();
		
		Map<NamedEntity, Set<KeywordType>> transliteratedNER = new HashMap<>();
//        transliteratedNER = gson.fromJson("{\"NamedEntity [type\\u003dPERSON, id\\u003dHI_PER_1167, entity\\u003danup jalota]\":[\"TAGS\"],\"NamedEntity [type\\u003dPERSON, id\\u003dPER_8224, entity\\u003dsonam kalra]\":[\"TAGS\"]}", transliteratedNER.getClass());
        
		
		String inptJson = "{\"transliteratedNER\":{\"NamedEntity [type\\u003dPERSON, id\\u003dHI_PER_1167, entity\\u003danup jalota]\":[\"TAGS\"],\"NamedEntity [type\\u003dPERSON, id\\u003dPER_8224, entity\\u003dsonam kalra]\":[\"TAGS\"]}}";
		
		
//		PostItem postItem1 = gson.fromJson(inptJson, collectionType);
		
		PostItem postItem2 = new PostItem();
		postItem2.setTransliteratedNER("testValue");
		
		inptJson = gson.toJson(postItem2);
		
		PostItem postItem = gson.fromJson(inptJson, PostItem.class);
        
		
		inptJson = "{\"transliteratedNER\":\"testValue\"}";
		postItem = gson.fromJson(inptJson, PostItem.class);
		
		System.out.println(inptJson);
        
        System.out.println("SUCCESS");
        
	}
	
	private static class PostItem implements Serializable {
//		private Map<NamedEntity, Set<KeywordType>> transliteratedNER = new HashMap<>();
		private String transliteratedNER;
		
		public String getTransliteratedNER() {
			return transliteratedNER;
		}
		
		public void setTransliteratedNER(String transliteratedNER) {
			this.transliteratedNER = transliteratedNER;
		}

	}
	

	private static enum NamedEntityType {
	    LOCATION("LOC"), PERSON("PER"), BUILDING_STREET_PLACE("BSP"), ORGANIZATION("ORG"), MOVIE("MVI"), LIFESTYLE("LIF");

	    private String name;

	    NamedEntityType(String name) {

	        this.name = name;
	    }
	    
	    public String getType() {
	        return name;
	    }
	    
	    public static NamedEntityType getNamedEntity(String name) {
	        if (StringUtils.isNotBlank(name)) {
	            for (NamedEntityType ne : NamedEntityType.values()) {
	                if (ne.name.equals(name.toUpperCase())) {
	                    return ne;
	                }
	            }
	        }
	        return null;
	    }
	}


	private static class NamedEntity {

	    private NamedEntityType type;
	    
	    private String id;
	    
	    private String entity;

	    
	    public NamedEntityType getType() {
	    
	        return type;
	    }

	    
	    public void setType(NamedEntityType type) {
	    
	        this.type = type;
	    }

	    
	    public String getEntity() {
	    
	        return entity;
	    }

	    
	    public void setEntity(String entity) {
	    
	        this.entity = entity;
	    }

	    @Override
	    public int hashCode() {

	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
	        result = prime * result + ((id == null) ? 0 : id.hashCode());
	        result = prime * result + ((type == null) ? 0 : type.hashCode());
	        return result;
	    }


	    @Override
	    public boolean equals(Object obj) {

	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        NamedEntity other = (NamedEntity) obj;
	        if (entity == null) {
	            if (other.entity != null)
	                return false;
	        } else if (!entity.equals(other.entity))
	            return false;
	        if (id == null) {
	            if (other.id != null)
	                return false;
	        } else if (!id.equals(other.id))
	            return false;
	        if (type != other.type)
	            return false;
	        return true;
	    }


	    @Override
	    public String toString() {

	        return "NamedEntity [type=" + type + ", id=" + id + ", entity=" + entity + "]";
	    }


	    public String getId() {

	        return id;
	    }


	    public void setId(String id) {

	        this.id = id;
	    }
	}

	
	private static enum KeywordType {
		
		TAGS(2.0), TITLE(1.5), TEXT(1.0), FIRST_WORD(0.0);
		
		private KeywordType(double weight) {
			this.weight = weight;
		};
		public double getWeight() {
			return weight;
		}
		private double weight;
	}

}
