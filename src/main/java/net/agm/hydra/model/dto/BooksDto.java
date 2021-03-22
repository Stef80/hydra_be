package net.agm.hydra.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.agm.hydra.datamodel.Status;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BooksDto {
	
	private String userEmail;
	private String bookableName;
	private Date startDate;
	private Date endDate;
	private Long tenantId;

	

}
