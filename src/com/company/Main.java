package com.company;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

enum flag {
    Harry,
    Filch,
    Cat,
    Cloak,
    Book,
    Exit,
    Empty,
    Occupied
}

class t_table {
    private double[][] table;
    t_table() {
        table = new double[][]{
                { 6.3138,12.7065,31.8193,63.6551,127.3447,318.4930,636.0450,},
                { 2.9200,4.3026,6.9646,9.9247,14.0887,22.3276,31.5989,},
                { 2.3534,3.1824,4.5407,5.8408,7.4534,10.2145,12.9242,},
                { 2.1319,2.7764,3.7470,4.6041,5.5976,7.1732,8.6103,},
                { 2.0150,2.5706,3.3650,4.0322,4.7734,5.8934,6.8688,},
                { 1.9432,2.4469,3.1426,3.7074,4.3168,5.2076,5.9589,},
                { 1.8946,2.3646,2.9980,3.4995,4.0294,4.7852,5.4079,},
                { 1.8595,2.3060,2.8965,3.3554,3.8325,4.5008,5.0414,},
                { 1.8331,2.2621,2.8214,3.2498,3.6896,4.2969,4.7809,},
                { 1.8124,2.2282,2.7638,3.1693,3.5814,4.1437,4.5869,},
                { 1.7959,2.2010,2.7181,3.1058,3.4966,4.0247,4.4369,},
                { 1.7823,2.1788,2.6810,3.0545,3.4284,3.9296,4.3178,},
                { 1.7709,2.1604,2.6503,3.0123,3.3725,3.8520,4.2208,},
                { 1.7613,2.1448,2.6245,2.9768,3.3257,3.7874,4.1404,},
                { 1.7530,2.1314,2.6025,2.9467,3.2860,3.7328,4.0728,},
                { 1.7459,2.1199,2.5835,2.9208,3.2520,3.6861,4.0150,},
                { 1.7396,2.1098,2.5669,2.8983,3.2224,3.6458,3.9651,},
                { 1.7341,2.1009,2.5524,2.8784,3.1966,3.6105,3.9216,},
                { 1.7291,2.0930,2.5395,2.8609,3.1737,3.5794,3.8834,},
                { 1.7247,2.0860,2.5280,2.8454,3.1534,3.5518,3.8495,},
                { 1.7207,2.0796,2.5176,2.8314,3.1352,3.5272,3.8193,},
                { 1.7172,2.0739,2.5083,2.8188,3.1188,3.5050,3.7921,},
                { 1.7139,2.0686,2.4998,2.8073,3.1040,3.4850,3.7676,},
                { 1.7109,2.0639,2.4922,2.7970,3.0905,3.4668,3.7454,},
                { 1.7081,2.0596,2.4851,2.7874,3.0782,3.4502,3.7251,},
                { 1.7056,2.0555,2.4786,2.7787,3.0669,3.4350,3.7067,},
                { 1.7033,2.0518,2.4727,2.7707,3.0565,3.4211,3.6896,},
                { 1.7011,2.0484,2.4671,2.7633,3.0469,3.4082,3.6739,},
                { 1.6991,2.0452,2.4620,2.7564,3.0380,3.3962,3.6594,},
                { 1.6973,2.0423,2.4572,2.7500,3.0298,3.3852,3.6459,},
                { 1.6955,2.0395,2.4528,2.7440,3.0221,3.3749,3.6334,},
                { 1.6939,2.0369,2.4487,2.7385,3.0150,3.3653,3.6218,},
                { 1.6924,2.0345,2.4448,2.7333,3.0082,3.3563,3.6109,},
                { 1.6909,2.0322,2.4411,2.7284,3.0019,3.3479,3.6008,},
                { 1.6896,2.0301,2.4377,2.7238,2.9961,3.3400,3.5912,},
                { 1.6883,2.0281,2.4345,2.7195,2.9905,3.3326,3.5822,},
                { 1.6871,2.0262,2.4315,2.7154,2.9853,3.3256,3.5737,},
                { 1.6859,2.0244,2.4286,2.7115,2.9803,3.3190,3.5657,},
                { 1.6849,2.0227,2.4258,2.7079,2.9756,3.3128,3.5581,},
                { 1.6839,2.0211,2.4233,2.7045,2.9712,3.3069,3.5510,},
                { 1.6829,2.0196,2.4208,2.7012,2.9670,3.3013,3.5442,},
                { 1.6820,2.0181,2.4185,2.6981,2.9630,3.2959,3.5378,},
                { 1.6811,2.0167,2.4162,2.6951,2.9591,3.2909,3.5316,},
                { 1.6802,2.0154,2.4142,2.6923,2.9555,3.2861,3.5258,},
                { 1.6794,2.0141,2.4121,2.6896,2.9521,3.2815,3.5202,},
                { 1.6787,2.0129,2.4102,2.6870,2.9488,3.2771,3.5149,},
                { 1.6779,2.0117,2.4083,2.6846,2.9456,3.2729,3.5099,},
                { 1.6772,2.0106,2.4066,2.6822,2.9426,3.2689,3.5051,},
                { 1.6766,2.0096,2.4049,2.6800,2.9397,3.2651,3.5004,},
                { 1.6759,2.0086,2.4033,2.6778,2.9370,3.2614,3.4960,},
                { 1.6753,2.0076,2.4017,2.6757,2.9343,3.2579,3.4917,},
                { 1.6747,2.0066,2.4002,2.6737,2.9318,3.2545,3.4877,},
                { 1.6741,2.0057,2.3988,2.6718,2.9293,3.2513,3.4838,},
                { 1.6736,2.0049,2.3974,2.6700,2.9270,3.2482,3.4800,},
                { 1.6730,2.0041,2.3961,2.6682,2.9247,3.2451,3.4764,},
                { 1.6725,2.0032,2.3948,2.6665,2.9225,3.2423,3.4730,},
                { 1.6720,2.0025,2.3936,2.6649,2.9204,3.2394,3.4696,},
                { 1.6715,2.0017,2.3924,2.6633,2.9184,3.2368,3.4663,},
                { 1.6711,2.0010,2.3912,2.6618,2.9164,3.2342,3.4632,},
                { 1.6706,2.0003,2.3901,2.6603,2.9146,3.2317,3.4602,},
                { 1.6702,1.9996,2.3890,2.6589,2.9127,3.2293,3.4573,},
                { 1.6698,1.9990,2.3880,2.6575,2.9110,3.2269,3.4545,},
                { 1.6694,1.9983,2.3870,2.6561,2.9092,3.2247,3.4518,},
                { 1.6690,1.9977,2.3860,2.6549,2.9076,3.2225,3.4491,},
                { 1.6686,1.9971,2.3851,2.6536,2.9060,3.2204,3.4466,},
                { 1.6683,1.9966,2.3842,2.6524,2.9045,3.2184,3.4441,},
                { 1.6679,1.9960,2.3833,2.6512,2.9030,3.2164,3.4417,},
                { 1.6676,1.9955,2.3824,2.6501,2.9015,3.2144,3.4395,},
                { 1.6673,1.9950,2.3816,2.6490,2.9001,3.2126,3.4372,},
                { 1.6669,1.9944,2.3808,2.6479,2.8987,3.2108,3.4350,},
                { 1.6666,1.9939,2.3800,2.6468,2.8974,3.2090,3.4329,},
                { 1.6663,1.9935,2.3793,2.6459,2.8961,3.2073,3.4308,},
                { 1.6660,1.9930,2.3785,2.6449,2.8948,3.2056,3.4288,},
                { 1.6657,1.9925,2.3778,2.6439,2.8936,3.2040,3.4269,},
                { 1.6654,1.9921,2.3771,2.6430,2.8925,3.2025,3.4250,},
                { 1.6652,1.9917,2.3764,2.6421,2.8913,3.2010,3.4232,},
                { 1.6649,1.9913,2.3758,2.6412,2.8902,3.1995,3.4214,},
                { 1.6646,1.9909,2.3751,2.6404,2.8891,3.1980,3.4197,},
                { 1.6644,1.9904,2.3745,2.6395,2.8880,3.1966,3.4180,},
                { 1.6641,1.9901,2.3739,2.6387,2.8870,3.1953,3.4164,},
                { 1.6639,1.9897,2.3733,2.6379,2.8859,3.1939,3.4147,},
                { 1.6636,1.9893,2.3727,2.6371,2.8850,3.1926,3.4132,},
                { 1.6634,1.9889,2.3721,2.6364,2.8840,3.1913,3.4117,},
                { 1.6632,1.9886,2.3716,2.6356,2.8831,3.1901,3.4101,},
                { 1.6630,1.9883,2.3710,2.6349,2.8821,3.1889,3.4087,},
                { 1.6628,1.9879,2.3705,2.6342,2.8813,3.1877,3.4073,},
                { 1.6626,1.9876,2.3700,2.6335,2.8804,3.1866,3.4059,},
                { 1.6623,1.9873,2.3695,2.6328,2.8795,3.1854,3.4046,},
                { 1.6622,1.9870,2.3690,2.6322,2.8787,3.1844,3.4032,},
                { 1.6620,1.9867,2.3685,2.6316,2.8779,3.1833,3.4020,},
                { 1.6618,1.9864,2.3680,2.6309,2.8771,3.1822,3.4006,},
                { 1.6616,1.9861,2.3676,2.6303,2.8763,3.1812,3.3995,},
                { 1.6614,1.9858,2.3671,2.6297,2.8755,3.1802,3.3982,},
                { 1.6612,1.9855,2.3667,2.6292,2.8748,3.1792,3.3970,},
                { 1.6610,1.9852,2.3662,2.6286,2.8741,3.1782,3.3959,},
                { 1.6609,1.9850,2.3658,2.6280,2.8734,3.1773,3.3947,},
                { 1.6607,1.9847,2.3654,2.6275,2.8727,3.1764,3.3936,},
                { 1.6606,1.9845,2.3650,2.6269,2.8720,3.1755,3.3926,},
                { 1.6604,1.9842,2.3646,2.6264,2.8713,3.1746,3.3915,},
                { 1.6602,1.9840,2.3642,2.6259,2.8706,3.1738,3.3905,},
                { 1.6601,1.9837,2.3638,2.6254,2.8700,3.1729,3.3894,},
                { 1.6599,1.9835,2.3635,2.6249,2.8694,3.1720,3.3885,},
                { 1.6598,1.9833,2.3631,2.6244,2.8687,3.1712,3.3875,},
                { 1.6596,1.9830,2.3627,2.6240,2.8682,3.1704,3.3866,},
                { 1.6595,1.9828,2.3624,2.6235,2.8675,3.1697,3.3856,},
                { 1.6593,1.9826,2.3620,2.6230,2.8670,3.1689,3.3847,},
                { 1.6592,1.9824,2.3617,2.6225,2.8664,3.1681,3.3838,},
                { 1.6591,1.9822,2.3614,2.6221,2.8658,3.1674,3.3829,},
                { 1.6589,1.9820,2.3611,2.6217,2.8653,3.1667,3.3820,},
                { 1.6588,1.9818,2.3607,2.6212,2.8647,3.1660,3.3812,},
                { 1.6587,1.9816,2.3604,2.6208,2.8642,3.1653,3.3803,},
                { 1.6586,1.9814,2.3601,2.6204,2.8637,3.1646,3.3795,},
                { 1.6585,1.9812,2.3598,2.6200,2.8632,3.1640,3.3787,},
                { 1.6583,1.9810,2.3595,2.6196,2.8627,3.1633,3.3779,},
                { 1.6582,1.9808,2.3592,2.6192,2.8622,3.1626,3.3771,},
                { 1.6581,1.9806,2.3589,2.6189,2.8617,3.1620,3.3764,},
                { 1.6580,1.9805,2.3586,2.6185,2.8612,3.1614,3.3756,},
                { 1.6579,1.9803,2.3583,2.6181,2.8608,3.1607,3.3749,},
                { 1.6578,1.9801,2.3581,2.6178,2.8603,3.1601,3.3741,},
                { 1.6577,1.9799,2.3578,2.6174,2.8599,3.1595,3.3735,},
                { 1.6575,1.9798,2.3576,2.6171,2.8594,3.1589,3.3727,},
                { 1.6574,1.9796,2.3573,2.6168,2.8590,3.1584,3.3721,},
                { 1.6573,1.9794,2.3571,2.6164,2.8585,3.1578,3.3714,},
                { 1.6572,1.9793,2.3568,2.6161,2.8582,3.1573,3.3707,},
                { 1.6571,1.9791,2.3565,2.6158,2.8577,3.1567,3.3700,},
                { 1.6570,1.9790,2.3563,2.6154,2.8573,3.1562,3.3694,},
                { 1.6570,1.9788,2.3561,2.6151,2.8569,3.1556,3.3688,},
                { 1.6568,1.9787,2.3559,2.6148,2.8565,3.1551,3.3682,},
                { 1.6568,1.9785,2.3556,2.6145,2.8561,3.1546,3.3676,},
                { 1.6567,1.9784,2.3554,2.6142,2.8557,3.1541,3.3669,},
                { 1.6566,1.9782,2.3552,2.6139,2.8554,3.1536,3.3663,},
                { 1.6565,1.9781,2.3549,2.6136,2.8550,3.1531,3.3658,},
                { 1.6564,1.9779,2.3547,2.6133,2.8546,3.1526,3.3652,},
                { 1.6563,1.9778,2.3545,2.6130,2.8542,3.1522,3.3646,},
                { 1.6562,1.9777,2.3543,2.6127,2.8539,3.1517,3.3641,},
                { 1.6561,1.9776,2.3541,2.6125,2.8536,3.1512,3.3635,},
                { 1.6561,1.9774,2.3539,2.6122,2.8532,3.1508,3.3630,},
                { 1.6560,1.9773,2.3537,2.6119,2.8529,3.1503,3.3624,},
                { 1.6559,1.9772,2.3535,2.6117,2.8525,3.1499,3.3619,},
                { 1.6558,1.9771,2.3533,2.6114,2.8522,3.1495,3.3614,},
                { 1.6557,1.9769,2.3531,2.6112,2.8519,3.1491,3.3609,},
                { 1.6557,1.9768,2.3529,2.6109,2.8516,3.1486,3.3604,},
                { 1.6556,1.9767,2.3527,2.6106,2.8512,3.1482,3.3599,},
                { 1.6555,1.9766,2.3525,2.6104,2.8510,3.1478,3.3594,},
                { 1.6554,1.9765,2.3523,2.6102,2.8506,3.1474,3.3589,},
                { 1.6554,1.9764,2.3522,2.6099,2.8503,3.1470,3.3584,},
                { 1.6553,1.9762,2.3520,2.6097,2.8500,3.1466,3.3579,},
                { 1.6552,1.9761,2.3518,2.6094,2.8497,3.1462,3.3575,},
                { 1.6551,1.9760,2.3516,2.6092,2.8494,3.1458,3.3570,},
                { 1.6551,1.9759,2.3515,2.6090,2.8491,3.1455,3.3565,},
                { 1.6550,1.9758,2.3513,2.6088,2.8489,3.1451,3.3561,},
                { 1.6549,1.9757,2.3511,2.6085,2.8486,3.1447,3.3557,},
                { 1.6549,1.9756,2.3510,2.6083,2.8483,3.1443,3.3552,},
                { 1.6548,1.9755,2.3508,2.6081,2.8481,3.1440,3.3548,},
                { 1.6547,1.9754,2.3507,2.6079,2.8478,3.1436,3.3544,},
                { 1.6547,1.9753,2.3505,2.6077,2.8475,3.1433,3.3540,},
                { 1.6546,1.9752,2.3503,2.6075,2.8472,3.1430,3.3536,},
                { 1.6546,1.9751,2.3502,2.6073,2.8470,3.1426,3.3531,},
                { 1.6545,1.9750,2.3500,2.6071,2.8467,3.1423,3.3528,},
                { 1.6544,1.9749,2.3499,2.6069,2.8465,3.1419,3.3523,},
                { 1.6544,1.9748,2.3497,2.6067,2.8463,3.1417,3.3520,},
                { 1.6543,1.9747,2.3496,2.6065,2.8460,3.1413,3.3516,},
                { 1.6543,1.9746,2.3495,2.6063,2.8458,3.1410,3.3512,},
                { 1.6542,1.9745,2.3493,2.6062,2.8455,3.1407,3.3508,},
                { 1.6542,1.9744,2.3492,2.6060,2.8452,3.1403,3.3505,},
                { 1.6541,1.9744,2.3490,2.6058,2.8450,3.1400,3.3501,},
                { 1.6540,1.9743,2.3489,2.6056,2.8448,3.1398,3.3497,},
                { 1.6540,1.9742,2.3487,2.6054,2.8446,3.1394,3.3494,},
                { 1.6539,1.9741,2.3486,2.6052,2.8443,3.1392,3.3490,},
                { 1.6539,1.9740,2.3485,2.6051,2.8441,3.1388,3.3487,},
                { 1.6538,1.9739,2.3484,2.6049,2.8439,3.1386,3.3483,},
                { 1.6537,1.9739,2.3482,2.6047,2.8437,3.1383,3.3480,},
                { 1.6537,1.9738,2.3481,2.6046,2.8435,3.1380,3.3477,},
                { 1.6537,1.9737,2.3480,2.6044,2.8433,3.1377,3.3473,},
                { 1.6536,1.9736,2.3478,2.6042,2.8430,3.1375,3.3470,},
                { 1.6536,1.9735,2.3477,2.6041,2.8429,3.1372,3.3466,},
                { 1.6535,1.9735,2.3476,2.6039,2.8427,3.1369,3.3464,},
                { 1.6535,1.9734,2.3475,2.6037,2.8424,3.1366,3.3460,},
                { 1.6534,1.9733,2.3474,2.6036,2.8423,3.1364,3.3457,},
                { 1.6534,1.9732,2.3472,2.6034,2.8420,3.1361,3.3454,},
                { 1.6533,1.9731,2.3471,2.6033,2.8419,3.1358,3.3451,},
                { 1.6533,1.9731,2.3470,2.6031,2.8416,3.1356,3.3448,},
                { 1.6532,1.9730,2.3469,2.6030,2.8415,3.1354,3.3445,},
                { 1.6532,1.9729,2.3468,2.6028,2.8413,3.1351,3.3442,},
                { 1.6531,1.9729,2.3467,2.6027,2.8411,3.1349,3.3439,},
                { 1.6531,1.9728,2.3466,2.6025,2.8409,3.1346,3.3436,},
                { 1.6531,1.9727,2.3465,2.6024,2.8407,3.1344,3.3433,},
                { 1.6530,1.9727,2.3463,2.6022,2.8406,3.1341,3.3430,},
                { 1.6529,1.9726,2.3463,2.6021,2.8403,3.1339,3.3428,},
                { 1.6529,1.9725,2.3461,2.6019,2.8402,3.1337,3.3425,},
                { 1.6529,1.9725,2.3460,2.6018,2.8400,3.1334,3.3422,},
                { 1.6528,1.9724,2.3459,2.6017,2.8398,3.1332,3.3419,},
                { 1.6528,1.9723,2.3458,2.6015,2.8397,3.1330,3.3417,},
                { 1.6528,1.9723,2.3457,2.6014,2.8395,3.1328,3.3414,},
                { 1.6527,1.9722,2.3456,2.6013,2.8393,3.1326,3.3411,},
                { 1.6527,1.9721,2.3455,2.6012,2.8392,3.1323,3.3409,},
                { 1.6526,1.9721,2.3454,2.6010,2.8390,3.1321,3.3406,},
                { 1.6526,1.9720,2.3453,2.6009,2.8388,3.1319,3.3403,},
                { 1.6525,1.9720,2.3452,2.6008,2.8387,3.1317,3.3401,},
                { 1.6525,1.9719,2.3451,2.6007,2.8385,3.1315,3.3398,}
        };
    }
    public double get_value(int degreeOfFreedom, int confidence) {
        return table[degreeOfFreedom-1][confidence];
    }
}

class DeadMoveExceptionInBackTracking extends Exception {
    DeadMoveExceptionInBackTracking(String message) {
        super(message);
    }
}

class DeadMoveExceptionInAStar extends Exception {
    DeadMoveExceptionInAStar(String message) {
        super(message);
    }
}

class Pair<T,V> {
    private T first;
    private V second;

    public Pair(T firstElement, V secondElement) {
        first = firstElement;
        second = secondElement;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}

class Cell {
    private flag typeOfCell;
    private int heuristicVal;
    private int moveVal;
    private int costVal;
    private int orderInBackTrack;
    private int orderInAStar;
    private boolean calculated;
    private boolean observedByBacktrack;
    private boolean visited;
    private boolean reachable;
    private boolean observed;
    private Pair<Integer,Integer> coordinate;
    private Pair<Integer,Integer> prevCell;

    public Cell(int first, int second) {
        setTypeOfCell(flag.Empty);
        calculated =  false;
        visited = false;
        reachable = false;
        observed = false;
        observedByBacktrack = false;
        this.coordinate = new Pair<>(first,second);
        costVal = 0;
        orderInBackTrack = 0;
        orderInAStar = 0;
    }

    public void setObservedByBacktrack(boolean observedByBacktrack) {
        this.observedByBacktrack = observedByBacktrack;
    }

    public boolean isObservedByBacktrack() {
        return observedByBacktrack;
    }

    public boolean isObserved() {
        return observed;
    }

    public void setObserved(boolean observed) {
        this.observed = observed;
    }
    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public int getOrderInAStar() {
        return orderInAStar;
    }

    public void setOrderInAStar(int orderInAStar) {
        this.orderInAStar = orderInAStar;
    }

    public int getOrderInBackTrack() {
        return orderInBackTrack;
    }

    public void setOrderInBackTrack(int orderInBackTrack) {
        this.orderInBackTrack = orderInBackTrack;
    }
    public Pair<Integer, Integer> getCoordinate() {
        return coordinate;
    }
    public boolean isCalculated() {
        return calculated;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Pair<Integer, Integer> getPrevCell() {
        return prevCell;
    }

    public void setPrevCell(int first, int second) {
        this.prevCell = new Pair<>(first,second);
    }

    public flag getTypeOfCell() {
        return typeOfCell;
    }

    public void setTypeOfCell(flag typeOfCell) {
        this.typeOfCell = typeOfCell;
        if (typeOfCell==flag.Filch) {
            moveVal = 2000;
        } else if (typeOfCell==flag.Cat) {
            moveVal = 2000;
        } else if (typeOfCell==flag.Occupied) {
            moveVal = 500;
        } else if (typeOfCell==flag.Harry) {
            moveVal = 0;
        } else {
            moveVal = 1;
        }
    }

    public int getHeuristicVal() {
        return heuristicVal;
    }

    public void setHeuristicVal(int heuristicVal) {
        this.heuristicVal = heuristicVal;
    }

    public int getMoveVal() {
        return moveVal;
    }

    public void setMoveVal(int moveVal) {
        this.moveVal = moveVal;
        costVal = moveVal + heuristicVal;
    }

    public void calculateCostVal(int prevVal) {
        calculated = true;
        costVal = moveVal + heuristicVal + prevVal;
    }

    public void calculateAsUnknown(int prevVal) {
        calculated = true;
        costVal = 1 + heuristicVal + prevVal;
    }

    public int getCostVal() {
        return costVal;
    }
}




class Map {
    private Cell[][] grid;
    public Pair<Integer,Integer> HarryCoordinate;
    public Pair<Integer,Integer> FilchCoordinate;
    public Pair<Integer,Integer> CatCoordinate;
    public Pair<Integer,Integer> BookCoordinate;
    public Pair<Integer,Integer> CloakCoordinate;
    public Pair<Integer,Integer> ExitCoordinate;
    private final SortedSet<Cell> cellSet;
    private static final int gridSize = 9;
    public Pair<Integer,Integer> reachableBookCoordinate;
    public Pair<Integer,Integer> reachableCloakCoordinate;
    public Pair<Integer,Integer> reachableExitCoordinate;
    private boolean cloakIsActivated;
    private boolean secondTypeOfVisionIsActivated;

    public Map() {
        grid = new Cell[gridSize][gridSize];
        for (int i=0; i<gridSize; i++) {
            for (int j=0 ; j<gridSize; j++) {
                grid[i][j] = new Cell(i,j);
            }
        }
        cellSet = new TreeSet<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                int costValCompare = Integer.compare(o1.getCostVal(), o2.getCostVal());
                if (costValCompare == 0)
                    return Integer.compare(o1.hashCode(), o2.hashCode());
                else
                    return costValCompare;
            }
        });
        reachableCloakCoordinate = new Pair<>(-1,-1);
        reachableExitCoordinate = new Pair<>(-1,-1);
        reachableBookCoordinate = new Pair<>(-1,-1);
        cloakIsActivated = false;
        secondTypeOfVisionIsActivated = false;
    }

    public void setSecondTypeOfVisionIsActivated(boolean secondTypeOfVisionIsActivated) {
        this.secondTypeOfVisionIsActivated = secondTypeOfVisionIsActivated;
    }

    public void activateCloak(int first, int second) {
        if (first == CloakCoordinate.getFirst() && second== CloakCoordinate.getSecond())
            cloakIsActivated =true;
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                if (grid[i][j].getTypeOfCell()==flag.Occupied)
                    grid[i][j].setMoveVal(1);
            }
        }
    }

    public void checkAllReachable(int first, int second) {
        SortedSet<Cell> reachableCellSet = new TreeSet<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                int costValCompare = Integer.compare(o1.getCostVal(), o2.getCostVal());
                if (costValCompare == 0)
                    return Integer.compare(o1.hashCode(), o2.hashCode());
                else
                    return costValCompare;
            }
        });
        grid[first][second].setReachable(true);
        reachableCellSet.add(grid[first][second]);
        while (reachableCellSet.size()!=0) {
            int f = reachableCellSet.first().getCoordinate().getFirst();
            int s = reachableCellSet.first().getCoordinate().getSecond();
            reachableCellSet.remove(reachableCellSet.first());
            for(int i=f-1; i<f+2; i++) {
                for(int j=s-1; j<s+2; j++) {
                    if (i>-1 && i<gridSize && j>-1 && j<gridSize && !grid[i][j].isReachable() &&
                            ((cloakIsActivated)  || !(grid[i][j].getTypeOfCell()==flag.Occupied) )
                            && !(grid[i][j].getTypeOfCell()==flag.Filch) &&
                            !(grid[i][j].getTypeOfCell()==flag.Cat)) {
                        grid[i][j].setReachable(true);
                        reachableCellSet.add(grid[i][j]);
                        if (grid[i][j].getTypeOfCell()==flag.Book)
                            reachableBookCoordinate = new Pair<>(i,j);
                        if (grid[i][j].getTypeOfCell()==flag.Cloak)
                            reachableCloakCoordinate = new Pair<>(i,j);
                        if (grid[i][j].getTypeOfCell()==flag.Exit)
                            reachableExitCoordinate = new Pair<>(i,j);
                    }
                }
            }
        }
    }

    public void resetAllReachable() {
        for (int i = 0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                grid[i][j].setReachable(false);
            }
        }
        reachableExitCoordinate = new Pair<>(-1,-1);
        reachableBookCoordinate = new Pair<>(-1,-1);
        reachableCloakCoordinate = new Pair<>(-1,-1);
    }

    public boolean isBookReachable() {
        return (reachableBookCoordinate.getFirst()!=-1 && reachableBookCoordinate.getSecond()!=-1);
    }

    public boolean isExitReachable() {
        return (reachableExitCoordinate.getFirst()!=-1 && reachableExitCoordinate.getSecond()!=-1);
    }

    public boolean isCloakReachable() {
        return (reachableCloakCoordinate.getFirst()!=-1 && reachableCloakCoordinate.getSecond()!=-1);
    }

    private void addCellsInSet(int first, int second) throws DeadMoveExceptionInAStar {
        cellSet.remove(cellSet.first());
        if (!secondTypeOfVisionIsActivated) {
            for(int i=first-1; i<first+2; i++) {
                for(int j=second-1; j<second+2; j++) {
                    if (i>-1 && i<gridSize && j>-1 && j<gridSize && !grid[i][j].isCalculated()) {
                        grid[i][j].calculateCostVal(grid[first][second].getCostVal());
                        grid[i][j].setPrevCell(first,second);
                        cellSet.add(grid[i][j]);
                    }
                }
            }
        } else {
            if (grid[first][second].getMoveVal()>10) {
                throw new DeadMoveExceptionInAStar("Dead end for A* algorithm!");
            } else {
                for (int i = first-2; i<first+3; i++) {
                    for (int j=second-2; j<second+3; j++) {
                        if (i>-1 && i<gridSize && j>-1 && j<gridSize && (Math.abs(i-first)==2 || Math.abs(j-second)==2) &&
                                (Math.abs(i-first)!=Math.abs(j-second)) && !grid[i][j].isObserved())
                            if (!grid[i][j].isObserved()) {
                                grid[i][j].setObserved(true);
                                if (grid[i][j].isCalculated()) {
                                    int f = grid[i][j].getPrevCell().getFirst();
                                    int s = grid[i][j].getPrevCell().getSecond();
                                    if (f != -1 && s != -1) {
                                        if (cellSet.contains(grid[i][j])){
                                            cellSet.remove( grid[i][j]);
                                            grid[i][j].calculateCostVal(grid[f][s].getCostVal());
                                            cellSet.add(grid[i][j]);
                                        } else {
                                            grid[i][j].calculateCostVal(grid[f][s].getCostVal());
                                        }
                                    }
                                }
                            }
                    }
                }
                for(int i=first-1; i<first+2; i++) {
                    for(int j=second-1; j<second+2; j++) {
                        if (i>-1 && i<gridSize && j>-1 && j<gridSize && grid[i][j].isObserved() && !grid[i][j].isCalculated()) {
                            grid[i][j].calculateCostVal(grid[first][second].getCostVal());
                            grid[i][j].setPrevCell(first,second);
                            cellSet.add(grid[i][j]);
                        } else  if (i>-1 && i<gridSize && j>-1 && j<gridSize && !grid[i][j].isObserved() && !grid[i][j].isCalculated()) {
                            grid[i][j].calculateAsUnknown(grid[first][second].getCostVal());
                            grid[i][j].setPrevCell(first,second);
                            cellSet.add(grid[i][j]);
                        }
                    }
                }
            }
        }
    }

    public void findWayUsingAStar(int firstS, int secondS, int firstE, int secondE) throws DeadMoveExceptionInAStar {
        for (int i=0; i<gridSize; i++) {
            for (int j=0 ; j<gridSize; j++) {
                grid[i][j].setHeuristicVal(Math.max( Math.abs(i-firstE),  Math.abs(j-secondE)));
            }
        }
//        printMap(0);
        grid[firstS][secondS].calculateCostVal(0);
        grid[firstS][secondS].setPrevCell(-1,-1);
        cellSet.add(grid[firstS][secondS]);
        addCellsInSet(firstS,secondS);

        while (cellSet.first().getCoordinate().getFirst() != firstE || cellSet.first().getCoordinate().getSecond() != secondE ) {
            //cellSet.remove(cellSet.first());
            addCellsInSet(cellSet.first().getCoordinate().getFirst(), cellSet.first().getCoordinate().getSecond());
        }
        ArrayList<Pair<Integer,Integer>> way = new ArrayList<>();
        Pair<Integer,Integer> currentCell = new Pair<>(firstE,secondE);

        while (currentCell.getFirst()!=-1 && currentCell.getSecond()!=-1) {
            way.add(currentCell);
            currentCell = new Pair<>(grid[currentCell.getFirst()][currentCell.getSecond()].getPrevCell().getFirst(),grid[currentCell.getFirst()][currentCell.getSecond()].getPrevCell().getSecond());
        }
        for (int i=way.size()-1; i>= 0; i--) {
            grid[way.get(i).getFirst()][way.get(i).getSecond()].setOrderInAStar(way.size()-i);
//            System.out.println(way.get(i).getFirst()+" "+way.get(i).getSecond());
        }
    }

    private boolean cellIsSafe(int first, int second) throws DeadMoveExceptionInBackTracking {
        if (!secondTypeOfVisionIsActivated) {
            if (first>-1 && second>-1 && first<gridSize && second<gridSize) {
                if (cloakIsActivated) {
                    return (grid[first][second].getTypeOfCell()!=flag.Filch
                            && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
                } else {
                    return (grid[first][second].getTypeOfCell() != flag.Occupied && grid[first][second].getTypeOfCell()!=flag.Filch
                            && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
                }
            } else {
                return false;
            }
        } else {
            for (int i = first-2; i<first+3; i++) {
                for (int j=second-2; j<second+3; j++) {
                    if (i>-1 && i<gridSize && j>-1 && j<gridSize && (Math.abs(i-first)==2 || Math.abs(j-second)==2) &&
                            (Math.abs(i-first)!=Math.abs(j-second)) && !grid[i][j].isObservedByBacktrack()) {
                        grid[i][j].setObservedByBacktrack(true);
                    }
                }
            }
            if (first>-1 && second>-1 && first<gridSize && second<gridSize && grid[first][second].isObservedByBacktrack()) {
                if (cloakIsActivated) {
                    return (grid[first][second].getTypeOfCell()!=flag.Filch
                            && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
                } else {
                    return (grid[first][second].getTypeOfCell() != flag.Occupied && grid[first][second].getTypeOfCell()!=flag.Filch
                            && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
                }
            } else if (first>-1 && second>-1 && first<gridSize && second<gridSize && !grid[first][second].isObservedByBacktrack()) {
                if (cloakIsActivated) {
                    if (grid[first][second].getTypeOfCell()==flag.Filch
                            || grid[first][second].getTypeOfCell()==flag.Cat) {
                        throw new DeadMoveExceptionInBackTracking("Dead end for backtrack algorithm!");
                    } else {
                        return (grid[first][second].getTypeOfCell()!=flag.Filch
                                && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
                    }
                } else {
                    if (grid[first][second].getTypeOfCell() == flag.Occupied) {
                        throw new DeadMoveExceptionInBackTracking("Dead end for backtrack algorithm!");
                    } else {
                        return (grid[first][second].getTypeOfCell() != flag.Occupied && grid[first][second].getTypeOfCell()!=flag.Filch
                                && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
                    }
                }
            } else {
                return false;
            }
        }
    }

    public boolean findWayUsingBacktracking(int firstS, int secondS, int firstE, int secondE, int orderInBacktrack) throws DeadMoveExceptionInBackTracking {
        if (orderInBacktrack==0 && secondTypeOfVisionIsActivated) {
            for (int i = firstS-2; i<firstS+3; i++) {
                for (int j=secondS-2; j<secondS+3; j++) {
                    if (i>-1 && i<gridSize && j>-1 && j<gridSize && (Math.abs(i-firstS)==2 || Math.abs(j-secondS)==2) &&
                            (Math.abs(i-firstS)!=Math.abs(j-secondS)) && !grid[i][j].isObservedByBacktrack()) {
                        grid[i][j].setObservedByBacktrack(true);
                    }
                }
            }
        }
        orderInBacktrack++;
        if (firstS==firstE && secondS == secondE) {
            grid[firstS][secondS].setOrderInBackTrack(orderInBacktrack);
            //System.out.println(firstS+" "+secondS);
            return true;
        } else {
            int up = Math.max(firstE - firstS, 0);
            int right = Math.max(secondE - secondS, 0);
            int down = Math.max(firstS - firstE, 0);
            int left = Math.max(secondS - secondE, 0);
            grid[firstS][secondS].setVisited(true);

            ArrayList<Integer> verticalMove = new ArrayList<>();
            ArrayList<Integer> horizontalMove = new ArrayList<>();

            if (up!=0) {
                verticalMove.add(1);
                verticalMove.add(0);
                verticalMove.add(-1);
                if (left!=0) {
                    horizontalMove.add(-1);
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                } else if (right!=0) {
                    horizontalMove.add(1);
                    horizontalMove.add(0);
                    horizontalMove.add(-1);
                } else {
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                    horizontalMove.add(-1);
                }
            } else if (down!=0) {
                verticalMove.add(-1);
                verticalMove.add(0);
                verticalMove.add(1);
                if (left!=0) {
                    horizontalMove.add(-1);
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                } else if (right!=0) {
                    horizontalMove.add(1);
                    horizontalMove.add(0);
                    horizontalMove.add(-1);
                } else {
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                    horizontalMove.add(-1);
                }
            } else {
                verticalMove.add(0);
                verticalMove.add(1);
                verticalMove.add(-1);
                if (left!=0) {
                    horizontalMove.add(-1);
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                } else if (right!=0) {
                    horizontalMove.add(1);
                    horizontalMove.add(0);
                    horizontalMove.add(-1);
                } else {
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                    horizontalMove.add(-1);
                }
            }

            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (verticalMove.get(i)!=0 || horizontalMove.get(j)!=0)
                        if (cellIsSafe(firstS+verticalMove.get(i),secondS+horizontalMove.get(j)) &&
                                findWayUsingBacktracking(firstS+verticalMove.get(i),secondS+horizontalMove.get(j), firstE, secondE, orderInBacktrack)) {
                            grid[firstS][secondS].setOrderInBackTrack(orderInBacktrack);
                            return true;
                        }
                }
            }
            return false;
        }
    }

    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    public void setHarry() {
        HarryCoordinate = new Pair<>(0,0);
        grid[HarryCoordinate.getFirst()][HarryCoordinate.getSecond()].setTypeOfCell(flag.Harry);
    }

    public void setFilch(int first, int second) {
        FilchCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Filch);
        for (int i=first-2; i<=first+2; i++) {
            for (int j=second-2; j<=second+2; j++) {
                if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                    if (grid[i][j].getTypeOfCell()==flag.Empty)
                       grid[i][j].setTypeOfCell(flag.Occupied);
                }
            }
        }
    }

    public void setCat(int first, int second) {
        CatCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Cat);
        for (int i=first-1; i<=first+1; i++) {
            for (int j=second-1; j<=second+1; j++) {
                if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                    if (grid[i][j].getTypeOfCell()==flag.Empty)
                        grid[i][j].setTypeOfCell(flag.Occupied);
                }
            }
        }
    }

    private void setBook(int first, int second) {
        BookCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Book);
    }

    private void setCloak(int first, int second) {
        CloakCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Cloak);
    }

    private void setExit(int first, int second) {
        ExitCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Exit);
    }

    public void generateMap() {
        Random rnd = new Random();
        setHarry();

        boolean notSuitable = true;
        int first = 0;
        int second = 0;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            for (int i=first-2; i<=first+2; i++) {
                for (int j=second-2; j<=second+2; j++) {
                    if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                        if (grid[i][j].getTypeOfCell()!=flag.Empty)
                            notSuitable = true;
                    }
                }
            }
        }
        setFilch(first, second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            for (int i=first-1; i<=first+1; i++) {
                for (int j=second-1; j<=second+1; j++) {
                    if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                        if (grid[i][j].getTypeOfCell()!=flag.Empty && grid[i][j].getTypeOfCell()!=flag.Occupied)
                            notSuitable = true;
                    }
                }
            }
        }
        setCat(first,second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            if (grid[first][second].getTypeOfCell()!=flag.Empty) {
                notSuitable = true;
            }
        }
        setBook(first,second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            if (grid[first][second].getTypeOfCell()!=flag.Empty) {
                notSuitable = true;
            }
        }
        setCloak(first,second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            if (grid[first][second].getTypeOfCell()!=flag.Empty) {
                notSuitable = true;
            }
        }
        setExit(first,second);
    }

    public void insertMap(int h1, int h2, int f1, int f2, int cat1, int cat2, int b1, int b2, int c1, int c2, int e1, int e2) {
        setHarry();
        setFilch(f1,f2);
        setCat(cat1,cat2);
        setBook(b1,b2);
        setCloak(c1,c2);
        setExit(e1,e2);
    }

    public void copyMap(Map someMap) {
        someMap.insertMap(HarryCoordinate.getFirst(),HarryCoordinate.getSecond(),
                FilchCoordinate.getFirst(), FilchCoordinate.getSecond(),
                CatCoordinate.getFirst(), CatCoordinate.getSecond(),
                BookCoordinate.getFirst(), BookCoordinate.getSecond(),
                CloakCoordinate.getFirst(), CloakCoordinate.getSecond(),
                ExitCoordinate.getFirst(), ExitCoordinate.getSecond());
        if (cloakIsActivated)
            someMap.activateCloak(CloakCoordinate.getFirst(), CloakCoordinate.getSecond());
        someMap.setSecondTypeOfVisionIsActivated(secondTypeOfVisionIsActivated);
    }

    public void copyAllObserved(Map someMap) {
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                grid[i][j].setObserved(someMap.grid[i][j].isObserved());
                grid[i][j].setObservedByBacktrack(someMap.grid[i][j].isObservedByBacktrack());
            }
        }
    }

    public void printMap(int style) {
        System.out.println("-------------------------------------------------------------------------");
        if (style == 0) {
            for (int i=gridSize-1; i>-1; i--) {
                for (int k=0; k<6; k++) {
                    for (int j=0; j<gridSize; j++) {
                        System.out.print('|');
                        if (grid[i][j].getTypeOfCell()==flag.Harry && k==0) {
                            System.out.print("\u001B[33m"+"H  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Filch && k==0) {
                            System.out.print("\u001B[31m"+"F  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cat && k==0) {
                            System.out.print("\u001B[31m"+"C  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cloak && k==0) {
                            System.out.print("\u001B[33m"+"IC "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Book && k==0) {
                            System.out.print("\u001B[33m"+"B  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Exit && k==0) {
                            System.out.print("\u001B[33m"+"E  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Occupied && k==0) {
                            System.out.print("\u001B[31m"+"O  "+"\u001B[0m");
                        } else if (k==1) {
                            if (grid[i][j].getOrderInAStar()<10 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+ grid[i][j].getOrderInAStar() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<100 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar() + " "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<1000 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar()+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else if (k==2) {
                            if (grid[i][j].getCostVal()<10) {
                                System.out.print(grid[i][j].getCostVal()+"  ");
                            } else if (grid[i][j].getCostVal()<100) {
                                System.out.print(grid[i][j].getCostVal()+" ");
                            } else {
                                System.out.print(grid[i][j].getCostVal());
                            }
                        }  else if (k==3) {
                            if (grid[i][j].getOrderInBackTrack()<10 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<100 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + " "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<1000 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack()+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else if (k==4) {
                            if (grid[i][j].isReachable()) {
                                System.out.print("\u001B[36m"+"R  "+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else if (k==5) {
                            if (grid[i][j].isObservedByBacktrack()) {
                                System.out.print("\u001B[36m"+"Ob "+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else {
                            System.out.print("   ");
                        }
                        for (int raw=3; raw<7; raw++) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println('|');
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        } else if (style==1) {
            for (int i=gridSize-1; i>-1; i--) {
                for (int k=0; k<3; k++) {
                    for (int j=0; j<gridSize; j++) {
                        System.out.print('|');
                        if (grid[i][j].getTypeOfCell()==flag.Harry && k==0) {
                            System.out.print("\u001B[33m"+"H  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Filch && k==0) {
                            System.out.print("\u001B[31m"+"F  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cat && k==0) {
                            System.out.print("\u001B[31m"+"C  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cloak && k==0) {
                            System.out.print("\u001B[33m"+"IC "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Book && k==0) {
                            System.out.print("\u001B[33m"+"B  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Exit && k==0) {
                            System.out.print("\u001B[33m"+"E  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Occupied && k==0) {
                            System.out.print("\u001B[31m"+"O  "+"\u001B[0m");
                        } else if (k==2) {
                            if (grid[i][j].getOrderInBackTrack()<10 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<100 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + " "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<1000 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack()+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else {
                            System.out.print("   ");
                        }
                        for (int raw=3; raw<7; raw++) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println('|');
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        } else if (style == 2) {
            for (int i=gridSize-1; i>-1; i--) {
                for (int k=0; k<3; k++) {
                    for (int j=0; j<gridSize; j++) {
                        System.out.print('|');
                        if (grid[i][j].getTypeOfCell()==flag.Harry && k==0) {
                            System.out.print("\u001B[33m"+"H   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Filch && k==0) {
                            System.out.print("\u001B[31m"+"F   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cat && k==0) {
                            System.out.print("\u001B[31m"+"C   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cloak && k==0) {
                            System.out.print("\u001B[33m"+"IC  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Book && k==0) {
                            System.out.print("\u001B[33m"+"B   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Exit && k==0) {
                            System.out.print("\u001B[33m"+"E   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Occupied && k==0) {
                            System.out.print("\u001B[31m"+"O   "+"\u001B[0m");
                        } else if (k==2) {
                            if (grid[i][j].getOrderInAStar()<10 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+ grid[i][j].getOrderInAStar() + "   "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<100 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<1000 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar()+" "+"\u001B[0m");
                            } else {
                                System.out.print("    ");
                            }
                        } else if (k==1) {
                            if (grid[i][j].getCostVal()<10) {
                                System.out.print(grid[i][j].getCostVal()+"   ");
                            } else if (grid[i][j].getCostVal()<100) {
                                System.out.print(grid[i][j].getCostVal()+"  ");
                            } else if (grid[i][j].getCostVal()<1000) {
                                System.out.print(grid[i][j].getCostVal()+" ");
                            } else {
                                System.out.print(grid[i][j].getCostVal());
                            }
                        } else {
                            System.out.print("    ");
                        }
                        for (int raw=4; raw<7; raw++) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println('|');
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }
}

public class Main {

    public static void calculateWayBE_Backtrack(Map map1, Map map2, AtomicInteger numberOfStepsInBacktrack) throws DeadMoveExceptionInBackTracking {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingBacktracking(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond(),0);
        map2.copyAllObserved(map1);
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingBacktracking(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableExitCoordinate.getFirst(), map2.reachableExitCoordinate.getSecond(),0);

        int fp1 = map1.reachableBookCoordinate.getFirst();
        int sp1 = map1.reachableBookCoordinate.getSecond();
        int fp2 = map2.reachableExitCoordinate.getFirst();
        int sp2 = map2.reachableExitCoordinate.getSecond();
        numberOfStepsInBacktrack.set(map1.getCell(fp1,sp1).getOrderInBackTrack()-1 +
                map2.getCell(fp2,sp2).getOrderInBackTrack()-1);
    }

    public static void calculateWayBE_AStar(Map map1, Map map2, AtomicInteger numberOfStepsInAStar) throws DeadMoveExceptionInAStar {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingAStar(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond());
        map2.copyAllObserved(map1);
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingAStar(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableExitCoordinate.getFirst(), map2.reachableExitCoordinate.getSecond());

        int fp1 = map1.reachableBookCoordinate.getFirst();
        int sp1 = map1.reachableBookCoordinate.getSecond();
        int fp2 = map2.reachableExitCoordinate.getFirst();
        int sp2 = map2.reachableExitCoordinate.getSecond();
        numberOfStepsInAStar.set(map1.getCell(fp1,sp1).getOrderInAStar()-1 +
                map2.getCell(fp2,sp2).getOrderInAStar()-1);
    }

    public static void calculateWayBCE_Backtrack(Map map1, Map map2, Map map3, AtomicInteger numberOfStepsInBacktrack) throws DeadMoveExceptionInBackTracking {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingBacktracking(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond(),0);
        map2.copyAllObserved(map1);
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingBacktracking(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond(),0);
        map3.copyAllObserved(map2);
        map3.activateCloak(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.checkAllReachable(map3.HarryCoordinate.getFirst(), map3.HarryCoordinate.getSecond());
        map3.findWayUsingBacktracking(map3.reachableCloakCoordinate.getFirst(), map3.reachableCloakCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond(),0);

        int fp1 = map1.reachableBookCoordinate.getFirst();
        int sp1 = map1.reachableBookCoordinate.getSecond();
        int fp2 = map2.reachableCloakCoordinate.getFirst();
        int sp2 = map2.reachableCloakCoordinate.getSecond();
        int fp3 = map3.reachableExitCoordinate.getFirst();
        int sp3 = map3.reachableExitCoordinate.getSecond();
        numberOfStepsInBacktrack.set(map1.getCell(fp1,sp1).getOrderInBackTrack()-1 +
                map2.getCell(fp2,sp2).getOrderInBackTrack()-1 +
                map3.getCell(fp3,sp3).getOrderInBackTrack()-1);
    }

    public static void calculateWayBCE_AStar(Map map1, Map map2, Map map3, AtomicInteger numberOfStepsInAStar) throws DeadMoveExceptionInAStar {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingAStar(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond());
        map2.copyAllObserved(map1);
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingAStar(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.copyAllObserved(map2);
        map3.activateCloak(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.checkAllReachable(map3.HarryCoordinate.getFirst(), map3.HarryCoordinate.getSecond());
        map3.findWayUsingAStar(map3.reachableCloakCoordinate.getFirst(), map3.reachableCloakCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond());

        int fp1 = map1.reachableBookCoordinate.getFirst();
        int sp1 = map1.reachableBookCoordinate.getSecond();
        int fp2 = map2.reachableCloakCoordinate.getFirst();
        int sp2 = map2.reachableCloakCoordinate.getSecond();
        int fp3 = map3.reachableExitCoordinate.getFirst();
        int sp3 = map3.reachableExitCoordinate.getSecond();
        numberOfStepsInAStar.set(map1.getCell(fp1,sp1).getOrderInAStar()-1 +
                map2.getCell(fp2,sp2).getOrderInAStar()-1 +
                map3.getCell(fp3,sp3).getOrderInAStar()-1);
    }

    public static void calculateWayCBE_Backtrack(Map map1, Map map2, Map map3, AtomicInteger numberOfStepsInBacktrack) throws DeadMoveExceptionInBackTracking {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingBacktracking(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond(),0);
        map2.copyAllObserved(map1);
        map2.activateCloak(map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond());
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingBacktracking(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond(),
                map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),0);
        map3.copyAllObserved(map2);
        map3.activateCloak(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.checkAllReachable(map3.HarryCoordinate.getFirst(), map3.HarryCoordinate.getSecond());
        map3.findWayUsingBacktracking(map3.reachableBookCoordinate.getFirst(), map3.reachableBookCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond(),0);

        int fp1 = map1.reachableCloakCoordinate.getFirst();
        int sp1 = map1.reachableCloakCoordinate.getSecond();
        int fp2 = map2.reachableBookCoordinate.getFirst();
        int sp2 = map2.reachableBookCoordinate.getSecond();
        int fp3 = map3.reachableExitCoordinate.getFirst();
        int sp3 = map3.reachableExitCoordinate.getSecond();
        numberOfStepsInBacktrack.set(map1.getCell(fp1,sp1).getOrderInBackTrack()-1 +
                map2.getCell(fp2,sp2).getOrderInBackTrack()-1 +
                map3.getCell(fp3,sp3).getOrderInBackTrack()-1);
    }

    public static void calculateWayCBE_AStar(Map map1, Map map2, Map map3, AtomicInteger numberOfStepsInAStar) throws  DeadMoveExceptionInAStar {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingAStar(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond());
        map2.copyAllObserved(map1);
        map2.activateCloak(map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond());
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingAStar(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond(),
                map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond());
        map3.copyAllObserved(map2);
        map3.activateCloak(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.checkAllReachable(map3.HarryCoordinate.getFirst(), map3.HarryCoordinate.getSecond());
        map3.findWayUsingAStar(map3.reachableBookCoordinate.getFirst(), map3.reachableBookCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond());

        int fp1 = map1.reachableCloakCoordinate.getFirst();
        int sp1 = map1.reachableCloakCoordinate.getSecond();
        int fp2 = map2.reachableBookCoordinate.getFirst();
        int sp2 = map2.reachableBookCoordinate.getSecond();
        int fp3 = map3.reachableExitCoordinate.getFirst();
        int sp3 = map3.reachableExitCoordinate.getSecond();
        numberOfStepsInAStar.set(map1.getCell(fp1,sp1).getOrderInAStar()-1 +
                map2.getCell(fp2,sp2).getOrderInAStar()-1 +
                map3.getCell(fp3,sp3).getOrderInAStar()-1);
    }

    public static void analyzeAllPossibleOutcomes_Backtrack(Map map, int scenario) throws DeadMoveExceptionInBackTracking {
        if (scenario==2)
            map.setSecondTypeOfVisionIsActivated(true);
        boolean usedBE = false;
        boolean usedCBE = false;
        boolean usedBCE = false;
        AtomicInteger numberOfStepsInBacktrackingBE = new AtomicInteger();
        AtomicInteger numberOfStepsInBacktrackingCBE = new AtomicInteger();
        AtomicInteger numberOfStepsInBacktrackingBCE = new AtomicInteger();
        Map map1 = new Map();
        Map map2 = new Map();
        Map map3 = new Map();
        Map map4 = new Map();
        Map map5 = new Map();
        Map map6 = new Map();
        Map map7 = new Map();
        Map map8 = new Map();
        map.copyMap(map1);
        map.copyMap(map2);
        map.copyMap(map3);
        map.copyMap(map4);
        map.copyMap(map5);
        map.copyMap(map6);
        map.copyMap(map7);
        map.copyMap(map8);
        map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());

        long startTime = System.currentTimeMillis();

        if (map.isBookReachable()) {
            if (map.isExitReachable()) {
                if (map.isCloakReachable()) {//BCE BE CBE
                    //BE
                    calculateWayBE_Backtrack(map1, map2, numberOfStepsInBacktrackingBE);
                    usedBE = true;

                    //BCE
                    calculateWayBCE_Backtrack(map3, map4, map5, numberOfStepsInBacktrackingBCE);
                    usedBCE = true;

                    //CBE
                    calculateWayCBE_Backtrack(map6, map7, map8, numberOfStepsInBacktrackingCBE);
                    usedCBE = true;

                } else {//BE
                    //BE
                    calculateWayBE_Backtrack(map1, map2, numberOfStepsInBacktrackingBE);
                    usedBE = true;

                }
            } else {
                if (map.isCloakReachable()) {
                    map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
                    map.resetAllReachable();
                    map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
                    if (map.isExitReachable()) {//BCE CBE
                        //BCE
                        calculateWayBCE_Backtrack(map3, map4, map5, numberOfStepsInBacktrackingBCE);
                        usedBCE = true;

                        //CBE
                        calculateWayCBE_Backtrack(map6, map7, map8, numberOfStepsInBacktrackingCBE);
                        usedCBE = true;

                    }
                }
            }
        } else if (map.isCloakReachable()) {
            map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
            map.resetAllReachable();
            map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
            if (map.isBookReachable()) {

                if (map.isExitReachable()) {//CBE
                    //CBE
                    calculateWayCBE_Backtrack(map6, map7, map8, numberOfStepsInBacktrackingCBE);
                    usedCBE = true;

                }
            }
        }

        long workingTime = System.currentTimeMillis() - startTime;

        if (usedBCE || usedBE || usedCBE) {
            int numOfStepsInBacktrackingBE = numberOfStepsInBacktrackingBE.get();
            int numOfStepsInBacktrackingBCE = numberOfStepsInBacktrackingBCE.get();
            int numOfStepsInBacktrackingCBE = numberOfStepsInBacktrackingCBE.get();

            ArrayList<Integer> bestForBacktracking = new ArrayList<>();
            bestForBacktracking.add(numOfStepsInBacktrackingBE);
            bestForBacktracking.add(numOfStepsInBacktrackingBCE);
            bestForBacktracking.add(numOfStepsInBacktrackingCBE);
            int minBacktrackingVal = 100000;
            int minBacktrackingInd = -1;
            for (int i=0; i<bestForBacktracking.size(); i++) {
                if (bestForBacktracking.get(i)<minBacktrackingVal && bestForBacktracking.get(i)!=0) {
                    minBacktrackingVal = bestForBacktracking.get(i);
                    minBacktrackingInd = i;
                }
            }

            if (scenario==2) {
                System.out.println("The shortest way using the Backtracking algorithm with 2nd type of perception takes "+ workingTime+" milliseconds and "+ minBacktrackingVal +" steps.");
            } else {
                System.out.println("The shortest way using the Backtracking algorithm with 1st type of perception takes "+ workingTime+" milliseconds and "+ minBacktrackingVal +" steps.");
            }
            if (minBacktrackingInd == 0) {
                System.out.println("Path would be Harry -- Book -- Exit:");
                System.out.println("Harry -> Book:");
                map1.printMap(1);
                System.out.println("Book -> Exit:");
                map2.printMap(1);
            } else if (minBacktrackingInd == 1){
                System.out.println("Path would be Harry -- Book -- Invisible Cloak -- Exit:");
                System.out.println("Harry -> Book:");
                map3.printMap(1);
                System.out.println("Book -> Invisible Cloak:");
                map4.printMap(1);
                System.out.println("Invisible Cloak -> Exit:");
                map5.printMap(1);
            } else {
                System.out.println("Path would be Harry -- Invisible Cloak -- Book  -- Exit:");
                System.out.println("Harry -> Invisible Cloak:");
                map6.printMap(1);
                System.out.println("Invisible Cloak -> Book:");
                map7.printMap(1);
                System.out.println("Book -> Exit:");
                map8.printMap(1);
            }
        } else {
            if(scenario==2) {
                System.out.println("You lose! (from Backtracking with 2nd type of perception)");
            } else {
                System.out.println("You lose! (from Backtracking with 1st type of perception)");
            }
        }
    }

    public static Pair<Integer,Long> analyzeAllPossibleOutcomes_Backtrack_withoutPrinting(Map map, int scenario) throws DeadMoveExceptionInBackTracking {
        if (scenario==2)
            map.setSecondTypeOfVisionIsActivated(true);
        boolean usedBE = false;
        boolean usedCBE = false;
        boolean usedBCE = false;
        AtomicInteger numberOfStepsInBacktrackingBE = new AtomicInteger();
        AtomicInteger numberOfStepsInBacktrackingCBE = new AtomicInteger();
        AtomicInteger numberOfStepsInBacktrackingBCE = new AtomicInteger();
        Map map1 = new Map();
        Map map2 = new Map();
        Map map3 = new Map();
        Map map4 = new Map();
        Map map5 = new Map();
        Map map6 = new Map();
        Map map7 = new Map();
        Map map8 = new Map();
        map.copyMap(map1);
        map.copyMap(map2);
        map.copyMap(map3);
        map.copyMap(map4);
        map.copyMap(map5);
        map.copyMap(map6);
        map.copyMap(map7);
        map.copyMap(map8);
        map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());

        long startTime = System.currentTimeMillis();

        if (map.isBookReachable()) {
            if (map.isExitReachable()) {
                if (map.isCloakReachable()) {//BCE BE CBE
                    //BE
                    calculateWayBE_Backtrack(map1, map2, numberOfStepsInBacktrackingBE);
                    usedBE = true;

                    //BCE
                    calculateWayBCE_Backtrack(map3, map4, map5, numberOfStepsInBacktrackingBCE);
                    usedBCE = true;

                    //CBE
                    calculateWayCBE_Backtrack(map6, map7, map8, numberOfStepsInBacktrackingCBE);
                    usedCBE = true;

                } else {//BE
                    //BE
                    calculateWayBE_Backtrack(map1, map2, numberOfStepsInBacktrackingBE);
                    usedBE = true;

                }
            } else {
                if (map.isCloakReachable()) {
                    map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
                    map.resetAllReachable();
                    map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
                    if (map.isExitReachable()) {//BCE CBE
                        //BCE
                        calculateWayBCE_Backtrack(map3, map4, map5, numberOfStepsInBacktrackingBCE);
                        usedBCE = true;

                        //CBE
                        calculateWayCBE_Backtrack(map6, map7, map8, numberOfStepsInBacktrackingCBE);
                        usedCBE = true;

                    }
                }
            }
        } else if (map.isCloakReachable()) {
            map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
            map.resetAllReachable();
            map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
            if (map.isBookReachable()) {

                if (map.isExitReachable()) {//CBE
                    //CBE
                    calculateWayCBE_Backtrack(map6, map7, map8, numberOfStepsInBacktrackingCBE);
                    usedCBE = true;

                }
            }
        }

        long workingTime = System.currentTimeMillis() - startTime;

        if (usedBCE || usedBE || usedCBE) {
            int numOfStepsInBacktrackingBE = numberOfStepsInBacktrackingBE.get();
            int numOfStepsInBacktrackingBCE = numberOfStepsInBacktrackingBCE.get();
            int numOfStepsInBacktrackingCBE = numberOfStepsInBacktrackingCBE.get();

            ArrayList<Integer> bestForBacktracking = new ArrayList<>();
            bestForBacktracking.add(numOfStepsInBacktrackingBE);
            bestForBacktracking.add(numOfStepsInBacktrackingBCE);
            bestForBacktracking.add(numOfStepsInBacktrackingCBE);
            int minBacktrackingVal = 100000;
            int minBacktrackingInd = -1;
            for (int i=0; i<bestForBacktracking.size(); i++) {
                if (bestForBacktracking.get(i)<minBacktrackingVal && bestForBacktracking.get(i)!=0) {
                    minBacktrackingVal = bestForBacktracking.get(i);
                    minBacktrackingInd = i;
                }
            }
            return new Pair<>(minBacktrackingVal, workingTime);
        }
        return new Pair<>(0, (long) 0);
    }

    public static void analyzeAllPossibleOutcomes_AStar(Map map, int scenario) throws DeadMoveExceptionInAStar {
        if (scenario==2)
            map.setSecondTypeOfVisionIsActivated(true);
        boolean usedBE = false;
        boolean usedCBE = false;
        boolean usedBCE = false;
        AtomicInteger numberOfStepsInAStarBE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarCBE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarBCE = new AtomicInteger();
        Map map1 = new Map();
        Map map2 = new Map();
        Map map3 = new Map();
        Map map4 = new Map();
        Map map5 = new Map();
        Map map6 = new Map();
        Map map7 = new Map();
        Map map8 = new Map();
        map.copyMap(map1);
        map.copyMap(map2);
        map.copyMap(map3);
        map.copyMap(map4);
        map.copyMap(map5);
        map.copyMap(map6);
        map.copyMap(map7);
        map.copyMap(map8);
        map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());

        long startTime = System.currentTimeMillis();

        if (map.isBookReachable()) {
            if (map.isExitReachable()) {
                if (map.isCloakReachable()) {//BCE BE CBE
                    //BE
                    calculateWayBE_AStar(map1, map2, numberOfStepsInAStarBE);
                    usedBE = true;

                    //BCE
                    calculateWayBCE_AStar(map3, map4, map5, numberOfStepsInAStarBCE);
                    usedBCE = true;

                    //CBE
                    calculateWayCBE_AStar(map6, map7, map8, numberOfStepsInAStarCBE);
                    usedCBE = true;

                } else {//BE
                    //BE
                    calculateWayBE_AStar(map1, map2, numberOfStepsInAStarBE);
                    usedBE = true;

                }
            } else {
                if (map.isCloakReachable()) {
                    map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
                    map.resetAllReachable();
                    map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
                    if (map.isExitReachable()) {//BCE CBE
                        //BCE
                        calculateWayBCE_AStar(map3, map4, map5,numberOfStepsInAStarBCE);
                        usedBCE = true;

                        //CBE
                        calculateWayCBE_AStar(map6, map7, map8, numberOfStepsInAStarCBE);
                        usedCBE = true;

                    }
                }
            }
        } else if (map.isCloakReachable()) {
            map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
            map.resetAllReachable();
            map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
            if (map.isBookReachable()) {

                if (map.isExitReachable()) {//CBE
                    //CBE
                    calculateWayCBE_AStar(map6, map7, map8, numberOfStepsInAStarCBE);
                    usedCBE = true;

                }
            }
        }
        long workingTime = System.currentTimeMillis() - startTime;

        if (usedBCE || usedBE || usedCBE) {
            int numOfStepsInAStarBE = numberOfStepsInAStarBE.get();
            int numOfStepsInAStarBCE = numberOfStepsInAStarBCE.get();
            int numOfStepsInAStarCBE = numberOfStepsInAStarCBE.get();

            ArrayList<Integer> bestForAStar = new ArrayList<>();
            bestForAStar.add(numOfStepsInAStarBE);
            bestForAStar.add(numOfStepsInAStarBCE);
            bestForAStar.add(numOfStepsInAStarCBE);
            int minAStarVal = 100000;
            int minAStarInd = -1;
            for (int i=0; i<bestForAStar.size(); i++) {
                if (bestForAStar.get(i)<minAStarVal && bestForAStar.get(i)!=0) {
                    minAStarVal = bestForAStar.get(i);
                    minAStarInd = i;
                }
            }
            if (scenario==2) {
                System.out.println("The shortest way using the A* algorithm with 2nd type of perception takes "+ workingTime+" milliseconds and "+ minAStarVal +" steps.");
            } else {
                System.out.println("The shortest way using the A* algorithm with 1st type of perception takes "+ workingTime+" milliseconds and "+ minAStarVal +" steps.");
            }
            if (minAStarInd == 0) {
                System.out.println("Path would be Harry -- Book -- Exit:");
                System.out.println("Harry -> Book:");
                map1.printMap(2);
                System.out.println("Book -> Exit:");
                map2.printMap(2);
            } else if (minAStarInd == 1){
                System.out.println("Path would be Harry -- Book -- Invisible Cloak -- Exit:");
                System.out.println("Harry -> Book:");
                map3.printMap(2);
                System.out.println("Book -> Invisible Cloak:");
                map4.printMap(2);
                System.out.println("Invisible Cloak -> Exit:");
                map5.printMap(2);
            } else {
                System.out.println("Path would be Harry -- Invisible Cloak -- Book  -- Exit:");
                System.out.println("Harry -> Invisible Cloak:");
                map6.printMap(2);
                System.out.println("Invisible Cloak -> Book:");
                map7.printMap(2);
                System.out.println("Book -> Exit:");
                map8.printMap(2);
            }
        } else {
            if(scenario==2) {
                System.out.println("You lose! (from A* with 2nd type of perception)");
            } else {
                System.out.println("You lose! (from A* with 1st type of perception)");
            }
        }
    }

    public static Pair<Integer,Long> analyzeAllPossibleOutcomes_AStar_withoutPrinting(Map map, int scenario) throws DeadMoveExceptionInAStar {
        if (scenario==2)
            map.setSecondTypeOfVisionIsActivated(true);
        boolean usedBE = false;
        boolean usedCBE = false;
        boolean usedBCE = false;
        AtomicInteger numberOfStepsInAStarBE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarCBE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarBCE = new AtomicInteger();
        Map map1 = new Map();
        Map map2 = new Map();
        Map map3 = new Map();
        Map map4 = new Map();
        Map map5 = new Map();
        Map map6 = new Map();
        Map map7 = new Map();
        Map map8 = new Map();
        map.copyMap(map1);
        map.copyMap(map2);
        map.copyMap(map3);
        map.copyMap(map4);
        map.copyMap(map5);
        map.copyMap(map6);
        map.copyMap(map7);
        map.copyMap(map8);
        map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());

        long startTime = System.currentTimeMillis();

        if (map.isBookReachable()) {
            if (map.isExitReachable()) {
                if (map.isCloakReachable()) {//BCE BE CBE
                    //BE
                    calculateWayBE_AStar(map1, map2, numberOfStepsInAStarBE);
                    usedBE = true;

                    //BCE
                    calculateWayBCE_AStar(map3, map4, map5, numberOfStepsInAStarBCE);
                    usedBCE = true;

                    //CBE
                    calculateWayCBE_AStar(map6, map7, map8, numberOfStepsInAStarCBE);
                    usedCBE = true;

                } else {//BE
                    //BE
                    calculateWayBE_AStar(map1, map2, numberOfStepsInAStarBE);
                    usedBE = true;

                }
            } else {
                if (map.isCloakReachable()) {
                    map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
                    map.resetAllReachable();
                    map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
                    if (map.isExitReachable()) {//BCE CBE
                        //BCE
                        calculateWayBCE_AStar(map3, map4, map5,numberOfStepsInAStarBCE);
                        usedBCE = true;

                        //CBE
                        calculateWayCBE_AStar(map6, map7, map8, numberOfStepsInAStarCBE);
                        usedCBE = true;

                    }
                }
            }
        } else if (map.isCloakReachable()) {
            map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
            map.resetAllReachable();
            map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
            if (map.isBookReachable()) {

                if (map.isExitReachable()) {//CBE
                    //CBE
                    calculateWayCBE_AStar(map6, map7, map8, numberOfStepsInAStarCBE);
                    usedCBE = true;

                }
            }
        }

        long workingTime = System.currentTimeMillis() - startTime;

        if (usedBCE || usedBE || usedCBE) {
            int numOfStepsInAStarBE = numberOfStepsInAStarBE.get();
            int numOfStepsInAStarBCE = numberOfStepsInAStarBCE.get();
            int numOfStepsInAStarCBE = numberOfStepsInAStarCBE.get();

            ArrayList<Integer> bestForAStar = new ArrayList<>();
            bestForAStar.add(numOfStepsInAStarBE);
            bestForAStar.add(numOfStepsInAStarBCE);
            bestForAStar.add(numOfStepsInAStarCBE);
            int minAStarVal = 100000;
            int minAStarInd = -1;
            for (int i=0; i<bestForAStar.size(); i++) {
                if (bestForAStar.get(i)<minAStarVal && bestForAStar.get(i)!=0) {
                    minAStarVal = bestForAStar.get(i);
                    minAStarInd = i;
                }
            }
            return new Pair<>(minAStarVal, workingTime);
        }
        return new Pair<>(0, (long) 0);
    }

    public static boolean isInputValid(int h1, int h2, int f1, int f2, int cat1, int cat2, int b1, int b2, int c1, int c2, int e1, int e2) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(h1);
        arrayList.add(h2);
        arrayList.add(b1);
        arrayList.add(b2);
        arrayList.add(c1);
        arrayList.add(c2);
        arrayList.add(e1);
        arrayList.add(e2);
        arrayList.add(f1);
        arrayList.add(f2);
        arrayList.add(cat1);
        arrayList.add(cat2);

        for (int i=0; i<arrayList.size(); i++) {
            if (arrayList.get(i)<0 || arrayList.get(i)>8)
                return false;
        }
        for (int i=0; i<arrayList.size(); i+=2) {
            for (int j=0; j<arrayList.size(); j+=2) {
                if (i!=j) {
                    if (arrayList.get(i) == arrayList.get(j) && arrayList.get(i+1)==arrayList.get(j+1))
                        return false;
                }
            }
        }
        for (int i=0; i<arrayList.size()-4; i+=2) {
            if (Math.abs(arrayList.get(i) - arrayList.get(10))<2 && Math.abs(arrayList.get(i+1) - arrayList.get(11))<2)
                return false;
            if ((Math.abs(arrayList.get(i) - arrayList.get(8))<3) && (Math.abs(arrayList.get(i+1) - arrayList.get(9))<3))
                return false;
        }
        return true;
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bool finding \"game\"!");
        System.out.println("Do you want to generate the map, insert the positions of agents manually or check statistics?");
        System.out.println("1 - generate the map");
        System.out.println("2 - insert the map");
        System.out.println("3 - check statistics");
        int firstChoose = scanner.nextInt();
        while (firstChoose!=1 && firstChoose!=2 && firstChoose!=3) {
            System.out.println("Wrong command - try again");
            System.out.println("1 - generate the map");
            System.out.println("2 - insert the map");
            System.out.println("3 - check statistics");
            firstChoose = scanner.nextInt();
        }
        if (firstChoose==3) {
            System.out.println("Statistics for Backtracking algorithm with 1st type of perception:");
            printStatisticalAnalysis(1,1);
            System.out.println("Statistics for Backtracking algorithm with 2nd type of perception:");
            printStatisticalAnalysis(1,2);
            System.out.println("Statistics for A* algorithm with 1st type of perception:");
            printStatisticalAnalysis(2,1);
            System.out.println("Statistics for A* algorithm with 2nd type of perception:");
            printStatisticalAnalysis(2,2);
        } else {
            System.out.println("What type of perception do you want to use?");
            System.out.println("1 - first type");
            System.out.println("2 - second type");
            System.out.println("3 - both types");
            int secondChoose = scanner.nextInt();
            while (secondChoose!=1 && secondChoose!=2 && secondChoose!=3) {
                System.out.println("Wrong command - try again");
                System.out.println("1 - first type");
                System.out.println("2 - second type");
                System.out.println("3 - both types");
                secondChoose = scanner.nextInt();
            }
            scanner.nextLine();

            if (firstChoose==2) {
                boolean isValid = false;
                String[] s= scanner.nextLine().split(" ");
                Integer[][] numbs = new Integer[6][2];
                while (!isValid) {

                    for (int i = 0; i<s.length; i++) {
                        numbs[i][0] = Integer.parseInt(s[i].split(",")[0].replace("[",""));
                        numbs[i][1] = Integer.parseInt(s[i].split(",")[1].replace("]",""));
                    }

                    isValid = isInputValid(numbs[0][0], numbs[0][1], numbs[1][0], numbs[1][1], numbs[2][0], numbs[2][1],
                            numbs[3][0], numbs[3][1], numbs[4][0], numbs[4][1], numbs[5][0], numbs[5][1]);
                    if (!isValid) {
                        System.out.println("Your input is invalid, please try again:");
                        s = scanner.nextLine().split(" ");
                    }
                }
                Map map = new Map();
                map.insertMap(numbs[0][0], numbs[0][1], numbs[1][0], numbs[1][1], numbs[2][0], numbs[2][1],
                        numbs[3][0], numbs[3][1], numbs[4][0], numbs[4][1], numbs[5][0], numbs[5][1]);
                if (secondChoose == 1) {
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(map,1);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(map, 1);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }
                } else if (secondChoose == 2) {
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(map,2);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(map, 2);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }
                } else {
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(map,1);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(map, 1);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }

                    Map mapForScenario2 = new Map();
                    map.copyMap(mapForScenario2);
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(mapForScenario2,2);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(mapForScenario2, 2);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }
                }
            } else {
                Map map = new Map();
                map.generateMap();
                if (secondChoose == 1) {
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(map,1);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(map, 1);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }
                } else if (secondChoose == 2) {
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(map,2);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(map, 2);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }
                } else {
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(map,1);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(map, 1);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }

                    Map mapForScenario2 = new Map();
                    map.copyMap(mapForScenario2);
                    try {
                        analyzeAllPossibleOutcomes_Backtrack(mapForScenario2,2);
                    } catch (DeadMoveExceptionInBackTracking e) {
                        System.out.println("Dead end for backtrack algorithm!");
                    } try {
                        analyzeAllPossibleOutcomes_AStar(mapForScenario2, 2);
                    } catch (DeadMoveExceptionInAStar e) {
                        System.out.println("Dead end for A* algorithm!");
                    }
                }
            }
        }
    }

    public static void printStatisticalAnalysis(int algorithm, int typeOfVision) {
        ArrayList<Integer> numberOfSteps = new ArrayList<>();
        ArrayList<Long> numberOfMilliseconds = new ArrayList<>();
        int maxNumOfSteps=0;
        int numOfWins=0;
        long maxValOfMilliseconds=0;
        int numberOfExperiments = 1000;
        System.out.println("Number of conducted experiments is "+numberOfExperiments);
        for (int i=0; i<numberOfExperiments; i++) {
            Map map = new Map();
            map.generateMap();
            try {
                Pair<Integer, Long> answer = new Pair<>(0,(long) 0);
                if (algorithm==1 && typeOfVision==1) {
                    answer = analyzeAllPossibleOutcomes_Backtrack_withoutPrinting(map, 1);
                } else if (algorithm==1 && typeOfVision==2) {
                    answer = analyzeAllPossibleOutcomes_Backtrack_withoutPrinting(map, 2);
                } else if (algorithm==2 && typeOfVision==1) {
                    answer = analyzeAllPossibleOutcomes_AStar_withoutPrinting(map, 1);
                } else if (algorithm==2 && typeOfVision==2) {
                    answer = analyzeAllPossibleOutcomes_AStar_withoutPrinting(map, 2);
                }

                int first = answer.getFirst();
                long second = answer.getSecond();

                if(first>0)
                    numOfWins++;

                if (first>maxNumOfSteps)
                    maxNumOfSteps = first;
                numberOfSteps.add(first);

                if (second>maxValOfMilliseconds)
                    maxValOfMilliseconds = second;
                numberOfMilliseconds.add(second);
            } catch (DeadMoveExceptionInAStar | DeadMoveExceptionInBackTracking e) {
                numberOfSteps.add(0);
                numberOfMilliseconds.add((long) 0);
            }
        }
        double winRate = (double) numOfWins /numberOfExperiments *100;
        System.out.println("Win rate is around "+String.format("%.1f", winRate)+"%");

        t_table table = new t_table();

        int N = maxNumOfSteps+1;
        int[] arrayOfSteps = new int[N];

        long M = maxValOfMilliseconds+1;
        Long[] arrayOfMilliseconds = new Long[(int) M];

        for (int i=0; i<N; i++) {
            arrayOfSteps[i] = 0;
        }

        for (int i=0; i<M; i++) {
            arrayOfMilliseconds[i] = (long) 0;
        }


        for (int i=0; i<numberOfSteps.size(); i++) {
            arrayOfSteps[numberOfSteps.get(i)]++;
        }
        double meanForSteps = 0;
        for (int i=0; i<N; i++) {
            meanForSteps += i*arrayOfSteps[i];
        }
        meanForSteps/=numberOfExperiments;

        double SxxForSteps = 0;
        for (int i=0; i<N; i++) {
            SxxForSteps+=Math.pow(meanForSteps-arrayOfSteps[i],2);
        }

        double tForSteps = table.get_value(N-1,1);
        double vForSteps = Math.sqrt(SxxForSteps / (numberOfExperiments * (numberOfExperiments - 1))) * tForSteps;
        double leftBorderForSteps = meanForSteps - vForSteps;
        double rightBorderForSteps = meanForSteps + vForSteps;
        System.out.println("Calculated mean number of steps is "+meanForSteps);
        System.out.println("Probability that expected number of steps would be from "+leftBorderForSteps+" to "+rightBorderForSteps+" is 95%");


        for (int i=0; i<numberOfMilliseconds.size(); i++) {
            arrayOfMilliseconds[Math.toIntExact(numberOfMilliseconds.get(i))]++;
        }
        double meanForMilliseconds = 0;
        for (int i=0; i<M; i++) {
            meanForMilliseconds += i*arrayOfMilliseconds[i];
        }
        meanForMilliseconds/=numberOfExperiments;

        double SxxForMilliseconds = 0;
        for (int i=0; i<M; i++) {
            SxxForMilliseconds+=Math.pow(meanForMilliseconds-arrayOfMilliseconds[i],2);
        }
        double tForMilliseconds = table.get_value((int) (M-1),1);
        double vForMilliseconds = Math.sqrt(SxxForMilliseconds / (numberOfExperiments * (numberOfExperiments - 1))) * tForMilliseconds;
        double leftBorderForMilliseconds = meanForMilliseconds - vForMilliseconds;
        double rightBorderForMilliseconds = meanForMilliseconds + vForMilliseconds;
        System.out.println("Calculated mean number of milliseconds is "+meanForMilliseconds);
        System.out.println();
    }

    public static void main(String[] args) {
        startGame();
    }
}