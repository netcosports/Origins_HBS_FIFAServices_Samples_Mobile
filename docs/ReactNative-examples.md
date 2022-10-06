# React native widgets example.

## Matches
There are four types of match widgets
- SmallMatches
- MediumMatches
- LargeMatches
- ExpandedMatches

```
<SmallMatches data={{ groupId: "255933"}} style={styles.smallMatches} />
<MediumMatches data={{ teamId: "43960"}} style={styles.mediumMatches} />
<LargeMatches data={{ roundId: "255951"}} style={styles.largeMatches} />
<ExpandedMatches data={{ roundId: "255951"}} style={styles.expanedMatches} />
```

It supports 4 types of data:
**Match id**, display matches for given round id:

```
<SmallMatches data={{matchId: "84872}} style={styles.smallMatches} />
```
**Round id**, display matches for given round:
```
<SmallMatches data={{roundId: "255951}} style={styles.smallMatches} />
```

**Group id**, display matches for given group:
```
<SmallMatches data={{roundId: "255933}} style={styles.smallMatches} />
```

**Team id**, display matches for given team:
```
<SmallMatches data={{roundId: "43960}} style={styles.smallMatches} />
```

## TopPlayer Stats widget

This widget display top players from given team for selected stats.
Supported stats type: **goals**, **shots**

<TopPlayerStats data={{ teamId: "43948", statsType: "goals" }} style={styles.topPlayerStats} />


## Team Matches Stats widget

This widget display stats of matches played by given team (by team id).
```
<TeamMatchesStats teamId="43960" style={styles.teamMatchesStats} />
```


import { Videos } from '@origins-digital/react-native-hbssdk';
import { Standings } from '@origins-digital/react-native-hbssdk';
import { Championship } from '@origins-digital/react-native-hbssdk';
import { Favorites } from '@origins-digital/react-native-hbssdk';
import { HeadToHead } from '@origins-digital/react-native-hbssdk';
import { TeamMatchesStats } from '@origins-digital/react-native-hbssdk';

import { TeamBoard } from '@origins-digital/react-native-hbssdk';
import { Venue } from '@origins-digital/react-native-hbssdk';
import { Watch } from '@origins-digital/react-native-hbssdk';
import { Lineup } from '@origins-digital/react-native-hbssdk';
import { MatchStats } from '@origins-digital/react-native-hbssdk';
import { MatchHeader } from '@origins-digital/react-native-hbssdk';
import { ExpandedMatchHeader } from '@origins-digital/react-native-hbssdk';
import { Actions } from '@origins-digital/react-native-hbssdk';
import { Squad } from '@origins-digital/react-native-hbssdk';
