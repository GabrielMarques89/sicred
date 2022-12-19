package design.boilerplate.springboot.model.mapper;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.Vote;
import design.boilerplate.springboot.model.dto.VoteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {

  VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

  Vote convertToVote(VoteRequest registrationRequest);

  Session mapSession(Long id);
}