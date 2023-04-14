package com.skanderjabouzi.nbacompose.helpers

import com.skanderjabouzi.nbacompose.models.db.TeamDetailsEntity
import com.skanderjabouzi.nbacompose.models.network.TeamDetails

object TeamDetailsEntityAdapter {
        fun teamDetailsEntityToTeamDetails(entity: TeamDetailsEntity): TeamDetails {
            return TeamDetails(
                entity.id,
                entity.region,
                entity.name,
                entity.abbrev,
                entity.pop,
                entity.imgURL
            )
        }

        fun teamDetailsToTeamDetailsEntity(details: TeamDetails): TeamDetailsEntity {
            return TeamDetailsEntity(
                details.id ?: 0,
                details.region ?: "",
                details.name ?: "",
                details.abbrev ?: "",
                details.pop ?: 0.0,
                details.imgURL ?: ""
            )
        }
}