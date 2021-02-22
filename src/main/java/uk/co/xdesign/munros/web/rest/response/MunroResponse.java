package uk.co.xdesign.munros.web.rest.response;

import org.apache.commons.collections.CollectionUtils;
import uk.co.xdesign.munros.dto.MunroDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MunroResponse implements Serializable {
    private static final long serialVersionUID = 2614054362417296531L;

    private String name;
    private String height;
    private String hillCategory;
    private String gridReference;

    public static MunroResponse fromDTO(MunroDTO munroDTO) {
        if(munroDTO == null) {
            return null;
        }
        MunroResponse response = new MunroResponse();
        response.setName(munroDTO.getName());
        response.setHeight(munroDTO.getHeightMeter());
        response.setHillCategory(munroDTO.getPost1997());
        response.setGridReference(munroDTO.getGridRef());
        return response;
    }

    public static List<MunroResponse> fromDtoList(List<MunroDTO> dtoList) {
        List<MunroResponse> responseList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dtoList)) {
            for(MunroDTO munroDTO : dtoList) {
                responseList.add(fromDTO(munroDTO));
            }
        }
        return responseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHillCategory() {
        return hillCategory;
    }

    public void setHillCategory(String hillCategory) {
        this.hillCategory = hillCategory;
    }

    public String getGridReference() {
        return gridReference;
    }

    public void setGridReference(String gridReference) {
        this.gridReference = gridReference;
    }
}
