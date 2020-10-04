from PIL import Image
from matplotlib import pyplot as plt
from pytesseract import *
import numpy as np
import re
import cv2
import pytesseract
from . import timeline
pytesseract.pytesseract.tesseract_cmd = r'C:\\Program Files\\Tesseract-OCR\\tesseract'
# pytesseract.pytesseract.tesseract_cmd = r'\\home\\ubuntu\\tesseract'
def module(gameId):
    print('22222')
# --------------------------------------------------------------------
# 이미지 구역 나누기v
    time = []
    for i in range(2):
        image = cv2.imread('./images/frame%d.jpg' % i)

        # b,g,r = cv2.split(image)
        # image2 = cv2.merge([r,g,b])
        
        # plt.imshow(image2) # 이미지 좌표 표시
        # plt.xticks([])
        # plt.yticks([])
        # plt.show()

        text_list = []
        #구역 설정 [가장 왼쪽 x좌표,가장왼쪽 y좌표,width,height]
        
        sections = [
        [448,8,15,13],# 불루팀 타워
        [844,8,15,13],# 레드팀 타워
        [504,7,35,17],# 불루팀 골드
        [760,7,35,17],# 레드팀 골드
        [602,11,28,20],# 불루팀 kill
        [662,11,27,20],# 레드팀 kill
        [586,573,22,135], # 불루팀 cs
        [681,573,22,135],# 레드팀 cs
        [628,50,14,13],# 분
        [645,50,14,13]# 초


        ]
        index = 0

        for section in sections:
            x,y,w,h = section
            img_trim = image[y:y+h, x:x+w]
            cv2.imwrite('org_trim.jpg', img_trim)
            org_image = cv2.imread('org_trim.jpg')
            # ------------------------------------------------------------------------------
            src = cv2.imread('org_trim.jpg', cv2.IMREAD_COLOR)
            big_src = cv2.resize(src, None, fx=8, fy=8, interpolation=cv2.INTER_CUBIC)
            gray = cv2.cvtColor(big_src, cv2.COLOR_BGR2GRAY)

            gray = cv2.threshold(gray, 120, 255, cv2.THRESH_TRUNC | cv2.THRESH_OTSU)[1] # 흑백화
            binary = cv2.threshold(gray,120,255, cv2.THRESH_TOZERO | cv2.THRESH_OTSU)[1]
            kernel = np.ones((1, 1), np.uint8)
            binary = cv2.dilate(binary, kernel, iterations=1)
            binary = cv2.erode(binary, kernel, iterations=1)
            thresh = cv2.threshold(binary,0,255, cv2.THRESH_BINARY)[1]
            thresh = cv2.threshold(binary,0,255, cv2.THRESH_BINARY_INV)[1]
            cv2.imwrite('result.jpg',thresh)
            print('middle')
            custom_config = r'--oem 3 --psm 6 outputbase digits'
            
            text = pytesseract.image_to_string(thresh,config=custom_config)

            tmp = list(map(str,text.split('\n')))
            tmp1 = []
            for num in tmp:
                if num:
                    if num[0].isdigit():
                        if index == 2 or index == 3 :
                            # 골드를 표현하는 부분일때
                            if len(num) == 3 and num.isdigit(): #3자리고 숫자만으로 구성되어 있으면
                                num = num[:2] + '.' + num[2:]
                        if index == 8 :
                            if int(num)>=90:
                                num = str(int(num)-80)
                            elif int(num)>=70:
                                num = str(int(num)-60)
                        if index == 9 :
                            if len(num)>=3:
                                num = num[:2]
                            elif int(num)> 60:
                                num = '59'
                        tmp1.append(num)


            text_list.append(tmp1)
            
            index += 1

        # print('불루팀 타워, 레드팀 타워, 불루팀 골드, 레드팀 골드, 불루팀 kill, 레드팀 kill, 불루팀 cs, 레드팀 cs,시간, 분' )
        # print(text_list)
        time.append(text_list[-2])
        time.append(text_list[-1])
    # print(time)
    return timeline.live_and_before([time[0],time[2]],gameId) # 분 단위만 넘기기