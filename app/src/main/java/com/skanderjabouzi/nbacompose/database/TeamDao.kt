package com.skanderjabouzi.nbacompose.database

import androidx.room.*
import com.skanderjabouzi.nbacompose.models.db.TeamDetailsEntity
import com.skanderjabouzi.nbacompose.models.db.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teamDetails: TeamDetailsEntity)

    @Query("select * from teamdetailsentity where id = :id")
    fun getTeamDetails(id: Int): Flow<TeamDetailsEntity>

    @Query("SELECT count(*) FROM teamdetailsentity WHERE id = :id")
    fun getTeamExists(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: TeamEntity)

    @Query("select * from teamentity")
    fun getTeams(): Flow<List<TeamEntity>>

    @Query("select count(*) from teamentity where 1")
    fun getTeamsCount(): Int
}
