package design.boilerplate.springboot.model.entities;

import design.boilerplate.springboot.model.enums.VoteResult;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VOTES", uniqueConstraints = {
    @UniqueConstraint(name = "uk_session_x_user",columnNames = {"session_id", "user_id"})})
public class Vote extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, foreignKey=@ForeignKey(name="fk_user"))
  private User user;

  @OneToOne
  @JoinColumn(name = "session_id", nullable = false, foreignKey=@ForeignKey(name="fk_session"))
  private Session session;

  @Column(nullable = false)
  private VoteResult voteResult;
}
