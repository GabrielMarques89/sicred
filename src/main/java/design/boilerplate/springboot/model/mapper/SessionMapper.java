package design.boilerplate.springboot.model.mapper;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.dto.SessionCreationResponse;
import design.boilerplate.springboot.model.dto.SessionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionMapper {

	SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

	@Mapping(target = "topic.id", source = "topic")
	Session convert(SessionRequest sessionRequest);
	SessionCreationResponse map(Session sessionRequest);
}
