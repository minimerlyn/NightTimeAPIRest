package com.esei.grvidal.nightTimeApi.model

import com.esei.grvidal.nightTimeApi.utlis.AnswerOptions
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator::class,
        property = "id")//todo no se como funcionara
class FriendRequest(
        @ManyToOne
        @JoinColumn(name="user_Asked")
        var userAsk: User,

        @ManyToOne
        @JoinColumn(name="user_Answer")
        var userAnswer: User,


) {
    var answer: AnswerOptions? = AnswerOptions.NOT_ANSWERED

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}
