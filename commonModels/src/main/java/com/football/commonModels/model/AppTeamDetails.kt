package com.football.commonModels.model


abstract class BaseTemData(
    open val id: Long,
    open val name: String,
    open val shortName: String,
    open val logo: String,
)

data class AppCompetitionTeam(
    override val id: Long,
    override val name: String,
    override val shortName: String,
    override val logo: String,
) : BaseTemData(id, name, shortName, logo)

data class AppTeamDetails(
    override val id: Long,
    override val name: String,
    override val shortName: String,
    override val logo: String,
    val founded: String,
    val venue: String,
    val coach: AppCoach,
    val players: List<AppPlayer>
) : BaseTemData(id, name, shortName, logo)

data class AppCoach(
    val id: Long,
    val name: String,
    val nationality: String
)

data class AppPlayer(
    val id: Long,
    val name: String,
    val position: String,
    val nationality: String
)
