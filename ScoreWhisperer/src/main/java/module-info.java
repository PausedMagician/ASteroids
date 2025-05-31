import PawzdCO.common.services.IScoreSPI;
import PawzdCO.scoreWhisperer.ScoreWhisperer;

module ScoreWhisperer {
    requires common;
    requires spring.web;
    requires java.net.http;
    provides IScoreSPI with ScoreWhisperer;
}
