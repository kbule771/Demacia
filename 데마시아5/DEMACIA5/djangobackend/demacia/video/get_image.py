# -*- coding: utf-8 -*-

import cv2
from . import change_text
 
# 영상의 의미지를 연속적으로 캡쳐할 수 있게 하는 class
def get_image_function(videoname):
    print(11111111111111111)
    # videoname = '10-19_KR-4664501922_06.mp4'
    vidcap = cv2.VideoCapture('./%s' % videoname)
    gameId = videoname[9:-7]

    count = 0

    length = int(vidcap.get(cv2.CAP_PROP_FRAME_COUNT))
    while(vidcap.isOpened()):
        ret, image = vidcap.read()
        if int(vidcap.get(1)) == 50:
            print('Saved frame number : ' + str(int(vidcap.get(1))))
            cv2.imwrite("./images/frame%d.jpg" % count, image)
            print('Saved frame%d.jpg' % count)
            count += 1
        elif int(vidcap.get(1)) % (length-50) == 0:
            print('Saved frame number : ' + str(int(vidcap.get(1))))
            cv2.imwrite("./images/frame%d.jpg" % count, image)
            print('Saved frame%d.jpg' % count)
            count += 1
        elif int(vidcap.get(1)) == (length-10):
            break

    return change_text.module(gameId)