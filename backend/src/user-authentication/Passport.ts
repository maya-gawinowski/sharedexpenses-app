import { Strategy, ExtractJwt } from 'passport-jwt';
import passport from 'passport';
import { JWT_SECRET } from 'src/config';

class Passport {
  public initialiseJWTStrategy() {
    const options = {
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
      secretOrKey: JWT_SECRET,
    };

    passport.use(
      new Strategy(options, (payload, done) => {
        try {
          return done(null, payload);
        } catch (error) {
          return done(error);
        }
      }),
    );
  }
}

export default new Passport();
