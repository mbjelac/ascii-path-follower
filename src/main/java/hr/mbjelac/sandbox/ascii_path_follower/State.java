package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class State {

    Coordinates location;
    Direction direction;
}
