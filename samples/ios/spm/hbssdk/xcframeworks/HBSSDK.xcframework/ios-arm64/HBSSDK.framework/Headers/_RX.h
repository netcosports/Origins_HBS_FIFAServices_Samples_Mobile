//
//  _RX.h
//  RxCocoa
//
//  Created by Krunoslav Zaher on 7/12/15.
//  Copyright © 2015 Krunoslav Zaher. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <objc/runtime.h>

/**
 ################################################################################
 This file is part of RX private API
 ################################################################################
 */

#if        TRACE_RESOURCES >= 2
#   define DLOG(...)         NSLog(__VA_ARGS__)
#else
#   define DLOG(...)
#endif

#if        DEBUG
#   define ABORT_IN_DEBUG    abort();
#else
#   define ABORT_IN_DEBUG
#endif


#define SEL_VALUE(x)      [NSValue valueWithPointer:(x)]
#define CLASS_VALUE(x)    [NSValue valueWithNonretainedObject:(x)]
#define IMP_VALUE(x)      [NSValue valueWithPointer:(x)]

/**
 Checks that the local `error` instance exists before assigning it's value by reference.
 This macro exists to work around static analysis warnings — `NSError` is always assumed to be `nullable`, even though we explicitly define the method parameter as `nonnull`. See http://www.openradar.me/21766176 for more details.
 */
#define RX_TRIVIATHROW_ERROR(errorValue, returnValue) if (error != nil) { *error = (errorValue); } return (returnValue);

#define RX_TRIVIACAT2(_1, _2) _RX_TRIVIACAT2(_1, _2)
#define _RX_TRIVIACAT2(_1, _2) _1 ## _2

#define RX_TRIVIAELEMENT_AT(n, ...) RX_TRIVIACAT2(_RX_TRIVIAELEMENT_AT_, n)(__VA_ARGS__)
#define _RX_TRIVIAELEMENT_AT_0(x, ...) x
#define _RX_TRIVIAELEMENT_AT_1(_0, x, ...) x
#define _RX_TRIVIAELEMENT_AT_2(_0, _1, x, ...) x
#define _RX_TRIVIAELEMENT_AT_3(_0, _1, _2, x, ...) x
#define _RX_TRIVIAELEMENT_AT_4(_0, _1, _2, _3, x, ...) x
#define _RX_TRIVIAELEMENT_AT_5(_0, _1, _2, _3, _4, x, ...) x
#define _RX_TRIVIAELEMENT_AT_6(_0, _1, _2, _3, _4, _5, x, ...) x

#define RX_TRIVIACOUNT(...) RX_TRIVIAELEMENT_AT(6, ## __VA_ARGS__, 6, 5, 4, 3, 2, 1, 0)
#define RX_TRIVIAEMPTY(...) RX_TRIVIAELEMENT_AT(6, ## __VA_ARGS__, 0, 0, 0, 0, 0, 0, 1)

/**
 #define SUM(context, index, head, tail) head + tail
 #define MAP(context, index, element) (context)[index] * (element)

 RX_TRIVIAFOR(numbers, SUM, MAP, b0, b1, b2);

 (numbers)[0] * (b0) + (numbers)[1] * (b1) + (numbers[2]) * (b2)
 */

#define RX_TRIVIAFOREACH(context, concat, map, ...) RX_TRIVIAFOR_MAX(RX_TRIVIACOUNT(__VA_ARGS__), _RX_TRIVIAFOREACH_CONCAT, _RX_TRIVIAFOREACH_MAP, context, concat, map, __VA_ARGS__)
#define _RX_TRIVIAFOREACH_CONCAT(index, head, tail, context, concat, map, ...) concat(context, index, head, tail)
#define _RX_TRIVIAFOREACH_MAP(index, context, concat, map, ...) map(context, index, RX_TRIVIAELEMENT_AT(index, __VA_ARGS__))

/**
 #define MAP(context, index, item) (context)[index] * (item)

 RX_TRIVIAFOR_COMMA(numbers, MAP, b0, b1);

 ,(numbers)[0] * b0, (numbers)[1] * b1
 */
#define RX_TRIVIAFOREACH_COMMA(context, map, ...) RX_TRIVIACAT2(_RX_TRIVIAFOREACH_COMMA_EMPTY_, RX_TRIVIAEMPTY(__VA_ARGS__))(context, map, ## __VA_ARGS__)
#define _RX_TRIVIAFOREACH_COMMA_EMPTY_1(context, map, ...)
#define _RX_TRIVIAFOREACH_COMMA_EMPTY_0(context, map, ...) , RX_TRIVIAFOR_MAX(RX_TRIVIACOUNT(__VA_ARGS__), _RX_TRIVIAFOREACH_COMMA_CONCAT, _RX_TRIVIAFOREACH_COMMA_MAP, context, map, __VA_ARGS__)
#define _RX_TRIVIAFOREACH_COMMA_CONCAT(index, head, tail, context, map, ...) head, tail
#define _RX_TRIVIAFOREACH_COMMA_MAP(index, context, map, ...) map(context, index, RX_TRIVIAELEMENT_AT(index, __VA_ARGS__))

// rx for

#define RX_TRIVIAFOR_MAX(max, concat, map, ...) RX_TRIVIACAT2(RX_TRIVIAFOR_, max)(concat, map, ## __VA_ARGS__)

#define RX_TRIVIAFOR_0(concat, map, ...)
#define RX_TRIVIAFOR_1(concat, map, ...) map(0, __VA_ARGS__)
#define RX_TRIVIAFOR_2(concat, map, ...) concat(1, RX_TRIVIAFOR_1(concat, map, ## __VA_ARGS__), map(1, __VA_ARGS__), __VA_ARGS__)
#define RX_TRIVIAFOR_3(concat, map, ...) concat(2, RX_TRIVIAFOR_2(concat, map, ## __VA_ARGS__), map(2, __VA_ARGS__), __VA_ARGS__)
#define RX_TRIVIAFOR_4(concat, map, ...) concat(3, RX_TRIVIAFOR_3(concat, map, ## __VA_ARGS__), map(3, __VA_ARGS__), __VA_ARGS__)
#define RX_TRIVIAFOR_5(concat, map, ...) concat(4, RX_TRIVIAFOR_4(concat, map, ## __VA_ARGS__), map(4, __VA_ARGS__), __VA_ARGS__)
#define RX_TRIVIAFOR_6(concat, map, ...) concat(5, RX_TRIVIAFOR_5(concat, map, ## __VA_ARGS__), map(5, __VA_ARGS__), __VA_ARGS__)

