package design.boilerplate.springboot.security.mapper;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.security.dto.AuthenticatedUserDto;
import design.boilerplate.springboot.security.dto.RegistrationRequest;
import design.boilerplate.springboot.security.dto.TopicRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TopicMapper {

	TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

	Topic convertToTopic(TopicRequest request);
}
