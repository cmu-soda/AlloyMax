
abstract sig Person {
  tags: set Tag
}
one sig P0,P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26,P27,P28,P29 extends Person {}

abstract sig Tag {}
one sig T0,T1,T2,T3,T4,T5,T6 extends Tag {}

fact {
  tags = P0->T0+P0->T1+P0->T2+P0->T3+P0->T4+P0->T5+P0->T6+
    P1->T3+P1->T6+
    P3->T0+P3->T3+P3->T4+
    P4->T1+P4->T3+P4->T4+P4->T6+
    P5->T0+P5->T2+P5->T3+P5->T5+P5->T6+
    P6->T0+P6->T5+
    P7->T0+P7->T1+P7->T2+P7->T4+P7->T5+
    P8->T0+P8->T1+P8->T2+P8->T3+P8->T4+P8->T5+P8->T6+
    P9->T0+P9->T1+P9->T2+P9->T3+P9->T4+P9->T5+P9->T6+
    P10->T4+
    P11->T4+
    P12->T4+
    P13->T2+P13->T3+P13->T4+P13->T6+
    P14->T4+
    P15->T0+P15->T2+P15->T3+P15->T4+P15->T5+P15->T6+
    P16->T0+P16->T1+P16->T2+P16->T3+P16->T4+P16->T5+P16->T6+
    P18->T2+
    P19->T6+
    P20->T1+P20->T2+P20->T4+P20->T5+
    P21->T1+P21->T2+P21->T3+P21->T6+
    P22->T0+P22->T1+P22->T2+P22->T3+P22->T5+
    P23->T6+
    P25->T0+P25->T1+P25->T2+P25->T3+P25->T4+P25->T5+P25->T6+
    P27->T0+P27->T1+P27->T2+P27->T3+P27->T4+P27->T5+P27->T6
}

sig Table {
  seat: set Person,
} {
  #seat < 8
  #seat > 2
}

fact {
  all p: Person | one seat.p
}

run {
  no seat': Table -> Person {
    all t: Table | #t.seat' < 8 and #t.seat' > 2
    all p: Person | one seat'.p
    #seat'.tags < #seat.tags
  }
} for exactly 6 Table, 7 int
