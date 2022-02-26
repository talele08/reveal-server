package com.revealprecision.revealserver.api.v1.facade.dto.factory;


import com.revealprecision.revealserver.api.v1.facade.dto.response.TeamMember;
import com.revealprecision.revealserver.persistence.domain.Organization;
import com.revealprecision.revealserver.persistence.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamMemberResponseFactory {

  public static TeamMember fromEntities(Organization organization, User user){
    TeamMember teamMember = TeamMember.builder().identifier(organization.getIdentifier().toString())
        .uuid(user.getIdentifier().toString()).build();
    return  teamMember;
  }
}
